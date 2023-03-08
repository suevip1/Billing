package com.pingpongx.smb.fee.api.dtos.cmd;

import lombok.Data;

import java.math.BigDecimal;
@Data
public class CouponInfo {
    String couponId;
    BigDecimal usdCredit;
    String templateId;
    /**
     * Billing , BeforeBilling , AfterBilling
     */
    String stage;
    /**
     *  credit 按额度核销 , count 按次数核销
     */
    String writeOffType;

    String repeatKey(){
        String split = "-";
        StringBuilder builder = new StringBuilder();
        builder.append(couponId).append(split).append(usdCredit);
        return builder.toString();
    }
}
