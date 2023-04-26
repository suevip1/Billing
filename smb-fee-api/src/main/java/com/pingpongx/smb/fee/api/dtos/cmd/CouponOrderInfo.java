package com.pingpongx.smb.fee.api.dtos.cmd;

import com.pingpongx.smb.fee.api.dtos.cmd.child.CouponInfo;
import com.pingpongx.smb.fee.api.dtos.cmd.child.PayeeInfo;
import com.pingpongx.smb.fee.api.dtos.cmd.child.PayerInfo;
import lombok.Data;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

@Data
public class CouponOrderInfo implements OrderHolder {
    private static final long serialVersionUID = 8061568762580326972L;
    OrderInfo orderInfo;

    BigDecimal totalFee;
    String totalFeeCurrency;

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
