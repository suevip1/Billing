package com.pingpongx.smb.fee.domain.convert;

import com.pingpongx.business.common.dto.Money;
import com.pingpongx.smb.fee.api.enums.CurrencyType;
import com.pingpongx.smb.fee.domain.runtime.BillingContext;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Optional;


public class ExchangeConvert {
    public static Money convert(CurrencyType currentCurrencyType,CurrencyType targetCurrencyType, BillingContext context,BigDecimal currentAmount){
        String sourceCurrencyCode = context.getRequest().getOrderInfo().getSourceCurrency();
        String targetCurrencyCode = context.getRequest().getOrderInfo().getTargetCurrency();
        if (currentCurrencyType.equals(targetCurrencyType)){
            Money ret = new Money();
            ret.setAmount(currentAmount);
            if (currentCurrencyType.equals(CurrencyType.Source)){
                ret.setCurrency(sourceCurrencyCode);
                ret.setAmount(currentAmount);
            }else{
                ret.setCurrency(targetCurrencyCode);
                ret.setAmount(currentAmount);
            }
            return ret;
        }

        String fxKey = sourceCurrencyCode + "_" + targetCurrencyCode;
        String fxKeyEx = targetCurrencyCode + "_" + sourceCurrencyCode;

        BigDecimal rate = Optional.ofNullable(context.getRequest().getFxRate()).map(r->r.get(fxKey)).map(r->r.getRate()).orElse(null);
        BigDecimal rateEx = Optional.ofNullable(context.getRequest().getFxRate()).map(r->r.get(fxKeyEx)).map(r->r.getRate()).orElse(null);
        Money ret;
        if (rate==null&&rateEx==null){
            throw new RuntimeException("rate not exists."+fxKey);
        }
        if (CurrencyType.Source.equals(currentCurrencyType)){
            ret = getMoney(currentAmount, targetCurrencyCode, rate, rateEx);
        }else {
            ret = getMoney(currentAmount, sourceCurrencyCode, rateEx, rate);
        }
        return ret;
    }

    private static Money getMoney(BigDecimal currentAmount, String currency, BigDecimal rate, BigDecimal rateEx) {
        BigDecimal r;
        r = rate;
        if (r == null){
            r = BigDecimal.ONE.divide(rateEx,9, RoundingMode.HALF_UP);
        }
        Money ret = new Money();
        ret.setCurrency(currency);
        ret.setAmount(currentAmount.multiply(r));
        return ret;
    }
}
