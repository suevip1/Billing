package com.pingpongx.smb.fee.api.dtos.cmd;

import lombok.Data;

import java.math.BigDecimal;
@Data
public class RateInfo {
    String sourceCode;
    String targetCode;
    BigDecimal rate;
    Long rateId;
    public static RateInfo of(String from,String target,String rate,Long rateId)
    {
        return of(from,target,new BigDecimal(rate),rateId);
    }

    public static RateInfo of(String from,String target,BigDecimal rate,Long rateId)
    {
        RateInfo ret = new RateInfo();
        ret.setRate(rate);
        ret.setSourceCode(from);
        ret.setTargetCode(target);
        ret.setRateId(rateId);
        return ret;
    }
}
