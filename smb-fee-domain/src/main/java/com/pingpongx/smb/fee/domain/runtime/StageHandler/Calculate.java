package com.pingpongx.smb.fee.domain.runtime.StageHandler;

import com.pingpongx.business.common.dto.Money;
import com.pingpongx.smb.fee.api.dtos.resp.Bill;
import com.pingpongx.smb.fee.api.dtos.resp.CostItemResult;
import com.pingpongx.smb.fee.domain.convert.ExchangeConvert;
import com.pingpongx.smb.fee.domain.enums.FeePayer;
import com.pingpongx.smb.fee.domain.module.CostItem;
import com.pingpongx.smb.fee.domain.module.event.CalculateCompleted;
import com.pingpongx.smb.fee.domain.module.event.MatchCompleted;
import com.pingpongx.smb.fee.domain.runtime.BillingContext;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
@Slf4j
public class Calculate extends BizFlowNode {

    @EventListener
    void calculate(MatchCompleted matchCompleted) {
        BillingContext context = matchCompleted.getContext();
        try {
            List<CostItem> costItemList = context.getMatchedCostItem();

            List<CostItemResult> result = groupByItemCode(costItemList).values().stream()
                    .map(list -> maxPriorityItems(list))
                    .flatMap(list -> list.stream())
                    .map(item -> calculateSingle(item, context))
                    .collect(Collectors.toList());
//            List<CostItemResult> result = costItemList.stream().sorted(Comparator.comparingInt(CostItem::getPriority))
//                    .map(costItem -> calculateSingle(costItem, context)).collect(Collectors.toList());
            Bill bill = new Bill();
            bill.setFeeResult(result);
            context.setBill(bill);
            CalculateCompleted calculateCompleted = new CalculateCompleted(context);
            applicationContext.publishEvent(calculateCompleted);
        } catch (Exception e) {
            handleException(context, e);
            log.error("error when calculate.", e);
        }
    }

    CostItemResult calculateSingle(CostItem item, BillingContext context) {
        CostItemResult costItemResult = new CostItemResult();
        costItemResult.setItemCode(item.getCode());
        costItemResult.setItemName(item.getDisplayName());
        try {
            BigDecimal bigDecimal = item.getCalculateExpress().fetchCalculator().getCalculateResult(context);
            Money ret = ExchangeConvert.convert(item.getCurrencyType(), FeePayer.valueOf(context.getRequest().getOrderInfo().getFeePayer()).getCurrencyType(), context, bigDecimal);
            costItemResult.setCurrency(ret.getCurrency());
            costItemResult.setAmount(ret.getAmount());
            costItemResult.setSuccess(true);
        } catch (Exception e) {
            costItemResult.setSuccess(false);
            costItemResult.setFailedReason(e.toString());
            log.error("error when calculate.", e);
        }
        return costItemResult;
    }

    Map<String, List<CostItem>> groupByItemCode(List<CostItem> costItemList) {
        Map<String, List<CostItem>> itemCollections = costItemList.stream()
                .collect(Collectors.groupingBy(costItem -> costItem.getCode(), Collectors.toList()));
        return itemCollections;
    }

    List<CostItem> maxPriorityItems(List<CostItem> costItems) {
        if (costItems.isEmpty()) {
            return new ArrayList<>();
        }
        if (costItems.size() == 1) {
            return costItems;
        }
        int priority = 0;
        List<CostItem> ret = new ArrayList<>();
        for (int i = 0; i < costItems.size(); i++) {
            CostItem costItem = costItems.get(i);
            if (costItem.getPriority() > priority) {
                ret = new ArrayList<>();
                priority = costItem.getPriority();
                ret.add(costItem);
            } else if (costItem.getPriority() == priority) {
                ret.add(costItem);
            }
        }
        //同code 同优先级 且都不是集合元素，不能重复
        long count = ret.stream().filter(c -> c.getCollectionCode() == null).count();
        if (count > 1) {
            throw new RuntimeException("matched more then 1 cost Item with same priority. costItemCode:" + costItems.get(0).getCode() + ", priority:" + ret.get(0).getPriority());
        }
        return ret;
    }

}
