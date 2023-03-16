package com.pingpongx.smb.fee.domain.convert.runtime;

import com.alibaba.fastjson2.JSON;
import com.pingpongx.smb.fee.api.dtos.cmd.BillingRequest;
import com.pingpongx.smb.fee.api.dtos.resp.Bill;
import com.pingpongx.smb.fee.dal.dataobject.BillingContextDo;
import com.pingpongx.smb.fee.dependency.convert.ConvertUtil;
import com.pingpongx.smb.fee.domain.module.CostItem;
import com.pingpongx.smb.fee.domain.runtime.BillingContext;
import org.apache.commons.lang3.StringUtils;

import java.util.Map;

public class BillingContextConvert {
    public static BillingContextDo toDo(BillingContext context) {
        return ConvertUtil.toDo(context, BillingContextDo.class, (one) -> {
            one.setId(context.getId());
            one.setBizOrderId(context.getRequest().getOrderInfo().getBizOrderId());
            one.setBizOrderType(context.getRequest().getOrderInfo().getBizOrderType());
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
        });
    }

    public static BillingContext toContext(BillingContextDo dbO) {
        return ConvertUtil.to(dbO, BillingContext.class, (one) -> {
            one.setId(dbO.getId());
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
        });
    }
}
