package com.pingpongx.smb.fee.domain.util;

import com.pingpongx.business.common.dto.Money;
import com.pingpongx.smb.fee.api.dtos.resp.Bill;
import com.pingpongx.smb.fee.api.dtos.resp.CostItemResult;
import com.pingpongx.smb.fee.domain.convert.ExchangeConvert;
import com.pingpongx.smb.fee.domain.enums.CurrencyType;
import com.pingpongx.smb.fee.domain.runtime.BillingContext;

import java.math.BigDecimal;
import java.util.Currency;

public class BillUtil {
    public static Money getTotalFee( BillingContext context){
        CurrencyType feePayer = CurrencyType.valueOf(context.getRequest().getOrderInfo().getFeePayer());
        Bill bill = context.getBill();
        return bill.getFeeResult().stream()
                .map(r-> ExchangeConvert.convert(getCurrencyType(r,context),feePayer,context,new BigDecimal(r.getAmount())))
                .reduce((m1,m2)->{
                    Money money = new Money();
                    money.setAmount(m1.getAmount().add(m2.getAmount()));
                    money.setCurrency(m1.getCurrency());
                    return money;
                }).orElse(new Money());
    }
    private static CurrencyType getCurrencyType(CostItemResult result, BillingContext context){
        if (context.getRequest().getOrderInfo().getSourceCurrency().equals(result.getCurrency())){
            return CurrencyType.Source;
        }
        return CurrencyType.Target;
    }
}
