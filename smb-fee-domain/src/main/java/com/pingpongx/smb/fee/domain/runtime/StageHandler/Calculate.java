package com.pingpongx.smb.fee.domain.runtime.StageHandler;

import com.pingpongx.smb.fee.api.dtos.resp.Bill;
import com.pingpongx.smb.fee.api.dtos.resp.CostItemResult;
import com.pingpongx.smb.fee.domain.enums.CurrencyType;
import com.pingpongx.smb.fee.domain.module.CostItem;
import com.pingpongx.smb.fee.domain.module.event.CalculateCompleted;
import com.pingpongx.smb.fee.domain.module.event.MatchCompleted;
import com.pingpongx.smb.fee.domain.runtime.BillingContext;
import lombok.RequiredArgsConstructor;
import org.joda.money.CurrencyUnit;
import org.joda.money.Money;
import org.springframework.context.ApplicationContext;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class Calculate {
    private final ApplicationContext applicationContext;

    @EventListener
    void calculate(MatchCompleted requestReceived) {
        BillingContext context = requestReceived.getContext();
        List<CostItem> costItemList = requestReceived.getContext().getMatchedCostItem();
        List<CostItemResult> result = costItemList.stream().sorted(Comparator.comparingInt(CostItem::getPriority))
                .map(costItem -> calculateSingle(costItem, context)).collect(Collectors.toList());
        Bill bill = new Bill();
        bill.setFeeResult(result);
        context.setBill(bill);
        CalculateCompleted calculateCompleted = new CalculateCompleted(context);
        applicationContext.publishEvent(calculateCompleted);
    }

    CostItemResult calculateSingle(CostItem item, BillingContext context) {
        CostItemResult costItemResult = new CostItemResult();
        costItemResult.setItemCode(item.getCode());
        costItemResult.setItemName(item.getDisplayName());
        BigDecimal bigDecimal = item.getCalculateExpress().getCalculator().getCalculateResult(context);
        String sourceCurrencyCode = context.getRequest().getOrderInfo().getSourceCurrency();
        String targetCurrencyCode = context.getRequest().getOrderInfo().getTargetCurrency();
        String fxKey = sourceCurrencyCode + "_" + targetCurrencyCode;
        Money fee;
        if (CurrencyType.Source.equals(item.getCurrencyType())) {
            fee = Money.of(CurrencyUnit.of(sourceCurrencyCode), bigDecimal);
        } else {
            bigDecimal = bigDecimal.multiply(context.getRequest().getFxRate().get(fxKey));
            fee = Money.of(CurrencyUnit.of(targetCurrencyCode), bigDecimal);
        }
        costItemResult.setFee(fee);
        return costItemResult;
    }
}
