package com.pingpongx.smb.fee.api.dtos.resp.coupon;

import lombok.Data;

import java.math.BigDecimal;
@Data
public class CreditDecrease extends CouponActionBase{
    BigDecimal amount;
    String currency;

    public static CreditDecrease of(String currency,BigDecimal amount){
        CreditDecrease ret = new CreditDecrease();
        ret.setAmount(amount);
        ret.setCurrency(currency);
        return ret;
    }
}
