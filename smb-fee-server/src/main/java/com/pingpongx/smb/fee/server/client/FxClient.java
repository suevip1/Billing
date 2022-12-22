package com.pingpongx.smb.fee.server.client;

import com.alibaba.cloud.commons.lang.StringUtils;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.pingpongx.business.common.exception.BizException;
import com.pingpongx.business.common.exception.ErrorCode;
import com.pingpongx.smb.fee.common.dto.FxGuidePriceExchangeDTO;
import com.pingpongx.smb.fee.server.config.FxConfig;
import com.pingpongx.smb.fee.server.utils.HttpUtil;
import lombok.extern.slf4j.Slf4j;
import org.assertj.core.util.Lists;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;

import java.util.*;
import java.util.concurrent.TimeUnit;

/**
 * @author wangcheng
 * @Description TODO
 * @createTime 2022年07月04日 20:26:00
 */
@Slf4j
@Component
public class FxClient {


    @Autowired
    private FxConfig fxConfig;

    @Autowired
    private RedisTemplate<String, String> redisTemplate;

    private final static String FX_GUIDE_PRICE_EXCHANGE_KEY = "fee:fx:guidePriceExchange";


    private Map<String, Object> buildRequestMap() {
        Map<String, Object> map = new HashMap<>();
//        map.put("sellCurrency", originCurrency);
//        map.put("buyCurrency", targetCurrency);
        map.put("sellAmount", "1");
        map.put("term", "T1");
//        map.put("clientId", clientId);
        map.put("nonce", (UUID.randomUUID().toString() + UUID.randomUUID().toString()).replaceAll("-", ""));
        map.put("timestamp", System.currentTimeMillis());
        return map;
    }

    /**
     * 查询市场指导价汇率
     *
     * @return
     */
    private List<FxGuidePriceExchangeDTO> getFxGuidePriceExchange() {
        try {
            FxConfig.ExchangeRatePair exchangeRatePair = fxConfig.getExchangeRatePair();
            if (Objects.isNull(exchangeRatePair)) {
                throw new BizException(ErrorCode.BIZ_BREAK, "获取汇率配置不存在");
            }
            Map<String, Object> map = buildRequestMap();
            map.put("userId", exchangeRatePair.getUserId());

            long start = System.currentTimeMillis();


            String fxGuidePriceUrl = fxConfig.getFxGuidePriceUrl();
            log.info("福鑫汇率地址查询请求，content = {},url = {}", JSONObject.toJSONString(map),fxGuidePriceUrl);
            String response = HttpUtil.get(map, exchangeRatePair.getKey(), fxGuidePriceUrl);
            log.info("福鑫接口耗时:{}ms, 请求福鑫获取汇率返回[{}]|入参情况,key:{}|userId:{}", System.currentTimeMillis() - start, response, exchangeRatePair.getKey(), exchangeRatePair.getUserId());

            JSONObject result = JSON.parseObject(response);
            if (StringUtils.isBlank(result.getString("data"))) {
                throw new BizException(ErrorCode.BIZ_BREAK, "获取汇率配置结果为空");
            }

            List<FxGuidePriceExchangeDTO> exchangeDTOList = JSONObject.parseArray(result.getString("data"), FxGuidePriceExchangeDTO.class);
            return exchangeDTOList;
        } catch (Exception e) {
            log.error("获取福鑫汇率异常", e);
        }
        return Lists.emptyList();
    }

    public List<FxGuidePriceExchangeDTO> getGuidePriceExchangeCache() {
        String s = redisTemplate.opsForValue().get(FX_GUIDE_PRICE_EXCHANGE_KEY);
        if (StringUtils.isBlank(s)) {
            List<FxGuidePriceExchangeDTO> fxGuidePriceExchange = getFxGuidePriceExchange();
            // 缓存24个小时
            if (fxGuidePriceExchange != null&&!fxGuidePriceExchange.isEmpty()){
                redisTemplate.opsForValue().set(FX_GUIDE_PRICE_EXCHANGE_KEY, JSONObject.toJSONString(fxGuidePriceExchange), 24L, TimeUnit.HOURS);
            }else{
                redisTemplate.opsForValue().set(FX_GUIDE_PRICE_EXCHANGE_KEY, JSONObject.toJSONString(fxGuidePriceExchange), 1L, TimeUnit.SECONDS);
            }
            return fxGuidePriceExchange;
        }
        return JSONObject.parseArray(s, FxGuidePriceExchangeDTO.class);
    }


    public static void main(String[] args) {

        FxClient fxClient = new FxClient();

        String clientId = "111";
        String originCurrency = "USD";
        String targetCurrency = "CNY";
        List<FxGuidePriceExchangeDTO> fxGuidePriceExchange = fxClient.getFxGuidePriceExchange();
        System.out.println(JSONObject.toJSONString(fxGuidePriceExchange));
    }

}
