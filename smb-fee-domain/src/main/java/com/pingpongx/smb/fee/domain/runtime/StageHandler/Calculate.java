package com.pingpongx.smb.fee.domain.runtime.StageHandler;

import com.pingpongx.smb.fee.api.dtos.resp.Bill;
import com.pingpongx.smb.fee.api.dtos.resp.CostItemResult;
import com.pingpongx.smb.fee.domain.enums.CurrencyType;
import com.pingpongx.smb.fee.domain.module.CostItem;
import com.pingpongx.smb.fee.domain.module.event.CalculateCompleted;
import com.pingpongx.smb.fee.domain.module.event.CouponParamUpdated;
import com.pingpongx.smb.fee.domain.runtime.BillingContext;
import org.joda.money.CurrencyUnit;
import org.joda.money.Money;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class Calculate extends BizFlowNode{

    @EventListener
    void calculate(CouponParamUpdated paramUpdated) {
        BillingContext context = paramUpdated.getContext();
        try{
            List<CostItem> costItemList = context.getMatchedCostItem();
            List<CostItemResult> result = costItemList.stream().sorted(Comparator.comparingInt(CostItem::getPriority))
                    .map(costItem -> calculateSingle(costItem, context)).collect(Collectors.toList());
            Bill bill = new Bill();
            bill.setFeeResult(result);
            context.setBill(bill);
            CalculateCompleted calculateCompleted = new CalculateCompleted(context);
            applicationContext.publishEvent(calculateCompleted);
        }catch (Exception e){
            handleException(context,e);
        }
    }

    CostItemResult calculateSingle(CostItem item, BillingContext context) {
        CostItemResult costItemResult = new CostItemResult();
        costItemResult.setItemCode(item.getCode());
        costItemResult.setItemName(item.getDisplayName());
        try{
            BigDecimal bigDecimal = item.getCalculateExpress().fetchCalculator().getCalculateResult(context);
            String sourceCurrencyCode = context.getRequest().getOrderInfo().getSourceCurrency();
            String targetCurrencyCode = context.getRequest().getOrderInfo().getTargetCurrency();
            String fxKey = sourceCurrencyCode + "_" + targetCurrencyCode;
            Money fee;
            if (CurrencyType.Source.equals(item.getCurrencyType())) {
//                fee = Money.of(CurrencyUnit.of(sourceCurrencyCode), bigDecimal);
                costItemResult.setCurrency(sourceCurrencyCode);
            } else {
                bigDecimal = bigDecimal.multiply(context.getRequest().getFxRate().get(fxKey));
//                fee = Money.of(CurrencyUnit.of(targetCurrencyCode), bigDecimal);
                costItemResult.setCurrency(targetCurrencyCode);
            }
            costItemResult.setAmount(bigDecimal.toString());
            costItemResult.setSuccess(true);
        }catch (Exception e){
            costItemResult.setSuccess(false);
            costItemResult.setFailedReason(e.getMessage());
        }
        return costItemResult;
    }
}
