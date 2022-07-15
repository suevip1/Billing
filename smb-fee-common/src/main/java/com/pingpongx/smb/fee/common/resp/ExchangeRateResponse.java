package com.pingpongx.smb.fee.common.resp;

import lombok.Builder;
import lombok.Data;

import java.io.Serializable;
import java.math.BigDecimal;

/**
 * @author wangcheng
 * @Description TODO
 * @createTime 2022年07月05日 11:25:00
 */
@Builder
@Data
public class ExchangeRateResponse implements Serializable {
    private static final long serialVersionUID = 2788212958006608299L;

    /**
     * 汇率
     */
    private BigDecimal exchangeRate;

    /**
     * 原始货币
     */
    private String originCurrency;

    /**
     * 目标货币
     */
    private String targetCurrency;

    /**
     * 更新时间
     */
    private Long updateTime;


}
