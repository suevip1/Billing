package com.pingpongx.smb.fee.domain.convert;

import com.alibaba.fastjson2.JSON;
import com.pingpongx.smb.fee.api.dtos.cmd.BillingRequest;
import com.pingpongx.smb.fee.api.dtos.resp.Bill;
import com.pingpongx.smb.fee.dal.dataobject.BillingContextDo;
import com.pingpongx.smb.fee.dal.dataobject.CostItemDo;
import com.pingpongx.smb.fee.dependency.convert.ConvertUtil;
import com.pingpongx.smb.fee.domain.module.CostItem;
import com.pingpongx.smb.fee.domain.runtime.BillingContext;
import org.apache.commons.lang3.StringUtils;

import java.util.Map;

public class CostItemConvert {

    public static CostItemDo toDo(CostItem item) {
        return ConvertUtil.toDo(item, CostItemDo.class, (one) -> {
//            one.setCalculateExpress(JSON.toJSONString(item.getCalculateExpress()));

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
        });
    }
}
