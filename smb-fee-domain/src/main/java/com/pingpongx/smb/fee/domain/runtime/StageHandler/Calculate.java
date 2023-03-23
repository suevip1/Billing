package com.pingpongx.smb.fee.domain.runtime.StageHandler;

import com.pingpongx.smb.fee.api.dtos.resp.Bill;
import com.pingpongx.smb.fee.api.dtos.resp.CostItemResult;
import com.pingpongx.smb.fee.domain.enums.CurrencyType;
import com.pingpongx.smb.fee.domain.module.CostItem;
import com.pingpongx.smb.fee.domain.module.event.CalculateCompleted;
import com.pingpongx.smb.fee.domain.module.event.CouponParamUpdated;
import com.pingpongx.smb.fee.domain.runtime.BillingContext;
import org.joda.money.Money;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class Calculate extends BizFlowNode {

    @EventListener
    void calculate(CouponParamUpdated paramUpdated) {
        BillingContext context = paramUpdated.getContext();
        try {
            List<CostItem> costItemList = context.getMatchedCostItem();

            List<CostItemResult> result =  groupByItemCode(costItemList).values().stream().map(list->maxPriorityItems(list)).flatMap(list->list.stream()).map(item->calculateSingle(item,context)).collect(Collectors.toList());
//            List<CostItemResult> result = costItemList.stream().sorted(Comparator.comparingInt(CostItem::getPriority))
//                    .map(costItem -> calculateSingle(costItem, context)).collect(Collectors.toList());
            Bill bill = new Bill();
            bill.setFeeResult(result);
            context.setBill(bill);
            CalculateCompleted calculateCompleted = new CalculateCompleted(context);
            applicationContext.publishEvent(calculateCompleted);
        } catch (Exception e) {
            handleException(context, e);
        }
    }

    CostItemResult calculateSingle(CostItem item, BillingContext context) {
        CostItemResult costItemResult = new CostItemResult();
        costItemResult.setItemCode(item.getCode());
        costItemResult.setItemName(item.getDisplayName());
        try {
            BigDecimal bigDecimal = item.getCalculateExpress().fetchCalculator().getCalculateResult(context);
            String sourceCurrencyCode = context.getRequest().getOrderInfo().getSourceCurrency();
            String targetCurrencyCode = context.getRequest().getOrderInfo().getTargetCurrency();
            String fxKey = sourceCurrencyCode + "_" + targetCurrencyCode;
            String fxKeyEx = targetCurrencyCode + "_" + sourceCurrencyCode;
            if (CurrencyType.Source.equals(item.getCurrencyType())) {
                costItemResult.setCurrency(sourceCurrencyCode);
            } else {
                BigDecimal rate = context.getRequest().getFxRate().get(fxKey);
                if (rate == null){
                    BigDecimal one = new BigDecimal(1);
                    rate = one.divide(context.getRequest().getFxRate().get(fxKeyEx),9, RoundingMode.HALF_UP);
                }
                if (rate == null){
                    throw  new RuntimeException("rate not exists."+fxKey);
                }
                bigDecimal = bigDecimal.multiply(rate);
                costItemResult.setCurrency(targetCurrencyCode);
            }
            costItemResult.setAmount(bigDecimal.toString());
            costItemResult.setSuccess(true);
        } catch (Exception e) {
            costItemResult.setSuccess(false);
            costItemResult.setFailedReason(e.toString());
        }
        return costItemResult;
    }

    Map<String, List<CostItem>> groupByItemCode(List<CostItem> costItemList){
        Map<String, List<CostItem>> itemCollections = costItemList.stream()
                .collect(Collectors.groupingBy(costItem -> costItem.getCode(), Collectors.toList()));
        return itemCollections;
    }

    List<CostItem> maxPriorityItems(List<CostItem> costItems) {
        if (costItems.isEmpty()){
            return new ArrayList<>();
        }
        if (costItems.size() == 1){
            return costItems;
        }
        int priority = 0;
        List<CostItem> ret = new ArrayList<>();
        for (int i = 0; i < costItems.size(); i++) {
            CostItem costItem = costItems.get(i);
            if (costItem.getPriority()>priority){
                ret = new ArrayList<>();
                priority = costItem.getPriority();
                ret.add(costItem);
            }else if (costItem.getPriority()==priority){
                ret.add(costItem);
            }
        }
        //同code 同优先级 且都不是集合元素，不能重复
        long count = ret.stream().filter(c->c.getCollectionCode()==null).count();
        if (count>1){
            throw new RuntimeException("matched more then 1 cost Item with same priority. costItemCode:"+costItems.get(0).getCode()+", priority:"+ret.get(0).getPriority());
        }
        return ret;
    }

}
