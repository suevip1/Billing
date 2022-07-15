package com.pingpongx.smb.fee.server.service.impl;

import com.alibaba.fastjson.JSONObject;
import com.pingpongx.smb.fee.common.dto.FxGuidePriceExchangeDTO;
import com.pingpongx.smb.fee.common.resp.ExchangeRateResponse;
import com.pingpongx.smb.fee.server.client.FxClient;
import com.pingpongx.smb.fee.server.service.FxExchangeService;
import lombok.extern.slf4j.Slf4j;
import org.assertj.core.util.Lists;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.math.BigDecimal;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author wangcheng
 * @Description TODO
 * @createTime 2022年07月05日 11:29:00
 */
@Slf4j
@Service
public class FxExchangeServiceImpl implements FxExchangeService {

    @Autowired
    private FxClient fxClient;

    @Override
    public List<ExchangeRateResponse> getAmountEstimateExchangeRate() {
        List<FxGuidePriceExchangeDTO> guidePriceExchangeList = fxClient.getGuidePriceExchangeCache();
        if (CollectionUtils.isEmpty(guidePriceExchangeList)) {
            log.info("获取汇率为空");
            return Lists.emptyList();
        }
        List<ExchangeRateResponse> responseList = guidePriceExchangeList.stream().map(g -> {
            BigDecimal divide = g.getBuy().add(g.getOffer()).divide(new BigDecimal("2"));
            String originCurrency = g.getSymbol().substring(0, 3);
            String targetCurrency = g.getSymbol().substring(3);
            return ExchangeRateResponse.builder()
                    .exchangeRate(divide)
                    .originCurrency(originCurrency)
                    .targetCurrency(targetCurrency)
                    .updateTime(g.getModified())
                    .build();
        }).collect(Collectors.toList());

        log.info("最终返回的资产预估汇率{}", JSONObject.toJSONString(responseList));
        return responseList;
    }
}
