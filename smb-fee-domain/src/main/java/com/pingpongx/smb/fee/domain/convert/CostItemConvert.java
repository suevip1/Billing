package com.pingpongx.smb.fee.domain.convert;

import com.alibaba.fastjson2.JSON;
import com.alibaba.fastjson2.JSONWriter;
import com.pingpongx.smb.export.module.Rule;
import com.pingpongx.smb.fee.api.dtos.cmd.BillingRequest;
import com.pingpongx.smb.fee.api.dtos.resp.Bill;
import com.pingpongx.smb.fee.dal.dataobject.BillingContextDo;
import com.pingpongx.smb.fee.dal.dataobject.CostItemDo;
import com.pingpongx.smb.fee.dependency.convert.ConvertUtil;
import com.pingpongx.smb.fee.domain.enums.CalculateMode;
import com.pingpongx.smb.fee.domain.enums.CurrencyType;
import com.pingpongx.smb.fee.domain.enums.Direction;
import com.pingpongx.smb.fee.domain.module.CostItem;
import com.pingpongx.smb.fee.domain.module.express.Expr;
import com.pingpongx.smb.fee.domain.runtime.BillingContext;
import com.pingpongx.smb.store.Codec;
import org.apache.commons.lang3.StringUtils;

import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.util.List;
import java.util.Map;
import java.util.Optional;

public class CostItemConvert {

    public static CostItemDo toDo(CostItem item) {
        return ConvertUtil.toDo(item, CostItemDo.class, (one) -> {
            one.setCalculateExpress(JSON.toJSONString(item.getCalculateExpress()));
            one.setMatchRule(JSON.toJSONString(item.getMatchRule().toDto(), JSONWriter.Feature.WriteClassName));
            one.setMatchVarKeys(JSON.toJSONString(item.getMatchVarKeys()));
            one.setCalculateExpress(JSON.toJSONString(item.getCalculateExpress().toDto(), JSONWriter.Feature.WriteClassName));
            one.setCalculateVarKeys(JSON.toJSONString(item.getCalculateVarKeys()));
            one.setMode(item.getMode().name());
            one.setCurrencyType(item.getCurrencyType().name());
            one.setInOrOut(item.getInOrOut().name());
            one.setStartTimeDisplay(LocalDateTime.ofEpochSecond(item.getStartTime(),0, ZoneOffset.UTC));
            one.setEndTimeDisplay(LocalDateTime.ofEpochSecond(item.getEndTime(),0, ZoneOffset.UTC));
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
