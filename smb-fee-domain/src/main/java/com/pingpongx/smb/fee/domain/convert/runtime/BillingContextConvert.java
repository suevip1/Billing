package com.pingpongx.smb.fee.domain.convert.runtime;

import com.alibaba.fastjson2.JSON;
import com.pingpongx.smb.fee.api.dtos.cmd.BillingRequest;
import com.pingpongx.smb.fee.api.dtos.resp.Bill;
import com.pingpongx.smb.fee.dal.dataobject.BillingContextDo;
import com.pingpongx.smb.fee.dependency.convert.ConvertUtil;
import com.pingpongx.smb.fee.domain.module.CostItem;
import com.pingpongx.smb.fee.domain.runtime.BillingContext;
import org.apache.commons.lang3.StringUtils;

import java.math.BigDecimal;
import java.util.Map;
import java.util.stream.Collectors;

public class BillingContextConvert {
    public static BillingContextDo toDo(BillingContext context) {
        return ConvertUtil.toDo(context, BillingContextDo.class, (one) -> {
            one.setBizOrderId(context.getRequest().getOrder().getBizOrderId());
            one.setBizOrderType(context.getRequest().getOrder().getBizOrderType());
            one.setRequestRepeatKey(context.getRequest().identify());
            if (context.getRequest() != null) {
                one.setRequest(JSON.toJSONString(context.getRequest()));
            }
            if (context.getParams() != null) {
                one.setParams(JSON.toJSONString(context.getParams()));
            }
            if (context.getBill() != null) {
                one.setBill(JSON.toJSONString(context.getBill()));
            }
            if (context.getMatchedCostItem() != null) {
                one.setMatchedCostItem(JSON.toJSONString(context.getMatchedCostItem()));
            }
            if (context.getCache() != null) {
                one.setCalculateResult(JSON.toJSONString(context.getCalculateResult()));
            }
            if (context.getFailedReasons() != null) {
                one.setFailedReasons(JSON.toJSONString(context.getFailedReasons()));
            }
        });
    }

    public static BillingContext toContext(BillingContextDo dbO) {
        return ConvertUtil.to(dbO, BillingContext.class, (one) -> {
            if (!StringUtils.isBlank(dbO.getRequest())) {
                one.setRequest(JSON.parseObject(dbO.getRequest(), BillingRequest.class));
            }
            if (!StringUtils.isBlank(dbO.getParams())) {
                one.setParams(JSON.parseObject(dbO.getParams(), Map.class));
            }
            if (!StringUtils.isBlank(dbO.getBill())) {
                one.setBill(JSON.parseObject(dbO.getBill(), Bill.class));
            }
            if (!StringUtils.isBlank(dbO.getMatchedCostItem())) {
                one.setMatchedCostItem(JSON.parseArray(dbO.getMatchedCostItem(), CostItem.class));
            }
            if (!StringUtils.isBlank(dbO.getCalculateResult())) {
//                Map<String,String> calculateResult = JSON.parseObject(dbO.getCalculateResult(), Map.class);
//                Map<String,BigDecimal> result = calculateResult.entrySet().stream().
//                        collect(Collectors.toMap(stringStringEntry -> stringStringEntry.getKey(),stringStringEntry -> new BigDecimal(stringStringEntry.getValue())));
//                one.setCalculateResult(result);
                one.setCalculateResult(JSON.parseObject(dbO.getCalculateResult(), Map.class));
            }
            if (!StringUtils.isBlank(dbO.getFailedReasons())) {
                one.setFailedReasons(JSON.parseObject(dbO.getFailedReasons(), Map.class));
            }

        });
    }
}
