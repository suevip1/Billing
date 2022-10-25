package com.pingpongx.smb.fee.server.config;

import com.alibaba.fastjson.JSONObject;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

import java.io.Serializable;

/**
 * @author wangcheng
 * @Description TODO
 * @createTime 2022年07月05日 10:09:00
 */
@Data
@Configuration
@Slf4j
public class FxConfig implements Serializable {
    private static final long serialVersionUID = -247675640974170443L;

    @Value("${fx_guide_price_request}")
    private String fxGuidePriceRequest;

    @Value("${fx_guide_price_url}")
    private String fxGuidePriceUrl;

    @Data
    public static class ExchangeRatePair {
        public String userId;
        public String key;
        public String clientId;
    }

    public ExchangeRatePair getExchangeRatePair() {
        return JSONObject.parseObject(getFxGuidePriceRequest(), ExchangeRatePair.class);
    }


}
