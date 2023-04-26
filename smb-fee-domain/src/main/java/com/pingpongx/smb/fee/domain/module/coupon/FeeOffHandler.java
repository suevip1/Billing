package com.pingpongx.smb.fee.domain.module.coupon;

import com.pingpongx.business.common.dto.Money;
import com.pingpongx.smb.asset.api.coupon.enums.CouponTypeEnum;
import com.pingpongx.smb.fee.api.dtos.cmd.child.CouponInfo;
import com.pingpongx.smb.fee.api.dtos.resp.CostItemResult;
import com.pingpongx.smb.fee.api.dtos.resp.CouponResult;
import com.pingpongx.smb.fee.api.dtos.resp.coupon.CreditDecrease;
import com.pingpongx.smb.fee.domain.convert.ExchangeConvert;
import com.pingpongx.smb.fee.domain.enums.CurrencyType;
import com.pingpongx.smb.fee.domain.enums.FeePayer;
import com.pingpongx.smb.fee.domain.runtime.BillingContext;
import com.pingpongx.smb.fee.domain.util.BillUtil;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;

@Component
public class FeeOffHandler implements AfterCalculateHandler {

    @Override
    public String supportedType() {
        return CouponTypeEnum.FEE_DEDUCTION.name();
    }

    @Override
    public void couponResultFix(CouponInfo couponInfo, BillingContext context) {
        CurrencyType feePayerCurrency = FeePayer.valueOf(context.getRequest().getOrderInfo().getFeePayer()).getCurrencyType();
        Money payerCredit = ExchangeConvert.convert(CurrencyType.valueOf(couponInfo.getCurrency()),feePayerCurrency,context,couponInfo.getValidValue());
        Money totalFee = BillUtil.getTotalFee(context);

        BigDecimal deducted = payerCredit.getAmount().min(totalFee.getAmount());
        CostItemResult costItemResult = new CostItemResult();
        costItemResult.setItemName("优惠券抵扣");
        costItemResult.setItemCode("couponDeducted");
        costItemResult.setCurrency(payerCredit.getCurrency());
        costItemResult.setSuccess(true);
        costItemResult.setAmount(deducted.multiply(new BigDecimal(-1)));

        context.getBill().getFeeResult().add(costItemResult);

        CouponResult couponResult = CouponResult.of(couponInfo,CreditDecrease.of(payerCredit.getCurrency(),deducted));
        context.getBill().getExpense().add(couponResult);
        return;
    }
}
