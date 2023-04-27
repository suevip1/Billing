package com.pingpongx.smb.fee.domain.module.coupon;

import com.pingpongx.business.common.dto.Money;
import com.pingpongx.smb.asset.api.coupon.enums.CouponTypeEnum;
import com.pingpongx.smb.fee.api.dtos.cmd.common.CouponInfo;
import com.pingpongx.smb.fee.api.dtos.resp.coupon.CouponAction;
import com.pingpongx.smb.fee.api.dtos.resp.coupon.CreditDecrease;
import com.pingpongx.smb.fee.domain.convert.ExchangeConvert;
import com.pingpongx.smb.fee.domain.enums.CurrencyType;
import com.pingpongx.smb.fee.domain.runtime.BillingContext;
import io.vavr.Tuple;
import io.vavr.Tuple3;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;

@Component
public class AmountOffHandler implements BeforeCalculateHandler {


    @Override
    public String supportedType() {
        return CouponTypeEnum.FEE_DEDUCTION.name();
    }

    public Tuple3<String, BigDecimal, CouponAction> couponParamFix(CouponInfo couponInfo, BillingContext context) {
        Money sourceCredit = ExchangeConvert.convert(CurrencyType.valueOf(couponInfo.getCurrency()), CurrencyType.Source, context, couponInfo.getValidValue());
        BigDecimal couponValidAmount = sourceCredit.getAmount();
        BigDecimal amount = context.getRequest().getOrderInfo().getAmount();
        BigDecimal creditCost = couponValidAmount.min(amount);
        BigDecimal finalAmount = amount.subtract(creditCost);
        if (finalAmount.equals(couponValidAmount)) {
            return Tuple.of("amount", finalAmount, CreditDecrease.of(couponInfo.getCurrency(), couponInfo.getValidValue()));
        }
        return Tuple.of("amount", finalAmount, CreditDecrease.of(sourceCredit.getCurrency(), creditCost));
    }

}
