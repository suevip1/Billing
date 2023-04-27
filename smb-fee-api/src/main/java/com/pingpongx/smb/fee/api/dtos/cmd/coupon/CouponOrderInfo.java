package com.pingpongx.smb.fee.api.dtos.cmd.coupon;

import com.pingpongx.smb.fee.api.dtos.cmd.OrderHolder;
import com.pingpongx.smb.fee.api.dtos.cmd.trade.OrderInfo;
import lombok.Data;

import java.math.BigDecimal;

@Data
public class CouponOrderInfo implements OrderHolder {
    private static final long serialVersionUID = 8061568762580326972L;
    OrderInfo orderInfo;

    BigDecimal totalFee;
    String totalFeeCurrency;

    String couponType;
    BigDecimal totalValidValue;
    String couponCurrency;

    @Override
    public String valid() {
        if (orderInfo.valid() != null){
            return orderInfo.valid();
        }
        if (orderInfo.valid() != null){
            return orderInfo.valid();
        }
        if (orderInfo.valid() != null){
            return orderInfo.valid();
        }
        if (orderInfo.valid() != null){
            return orderInfo.valid();
        }
        if (orderInfo.valid() != null){
            return orderInfo.valid();
        }

        return null ;
    }

    @Override
    public String getBizOrderId() {
        return orderInfo.getBizOrderId();
    }

    @Override
    public String getBizOrderType() {
        return orderInfo.getBizOrderType();
    }

    @Override
    public String getFeePayer() {
        return orderInfo.getFeePayer();
    }

    @Override
    public String getSourceCurrency() {
        return orderInfo.getSourceCurrency();
    }

    @Override
    public String getTargetCurrency() {
        return orderInfo.getTargetCurrency();
    }

    @Override
    public BigDecimal getAmount() {
        return orderInfo.getAmount();
    }

    @Override
    public String getIdentify() {
        return orderInfo.getBizOrderType()+"-"+orderInfo.getBizOrderId();
    }

}
