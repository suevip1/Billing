package com.pingpongx.smb.fee.domain.convert.runtime;

import com.alibaba.fastjson2.JSON;
import com.alibaba.fastjson2.JSONWriter;
import com.pingpongx.smb.fee.api.dtos.cmd.BillingRequest;
import com.pingpongx.smb.fee.api.dtos.resp.Bill;
import com.pingpongx.smb.fee.dal.dataobject.BillingContextDo;
import com.pingpongx.smb.fee.dal.dataobject.CostItemDo;
import com.pingpongx.smb.fee.dal.repository.CostItemRepository;
import com.pingpongx.smb.fee.dependency.convert.ConvertUtil;
import com.pingpongx.smb.fee.domain.factory.CostItemFactory;
import com.pingpongx.smb.fee.domain.module.CostItem;
import com.pingpongx.smb.fee.domain.module.Request;
import com.pingpongx.smb.fee.domain.runtime.BillingContext;
import lombok.RequiredArgsConstructor;
import org.apache.commons.lang3.StringUtils;
import org.bouncycastle.cert.ocsp.Req;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
@Component
@RequiredArgsConstructor
public class BillingContextConvert {
    private final CostItemRepository costItemRepository;
    private final CostItemFactory factory;
    public  BillingContextDo toDo(BillingContext context) {
        return ConvertUtil.toDo(context, BillingContextDo.class, (one) -> {
            one.setId(context.getId());
            one.setBizOrderId(context.getRequest().getOrderInfo().getBizOrderId());
            one.setBizOrderType(context.getRequest().getOrderInfo().getBizOrderType());
            one.setRequestRepeatKey(context.getRequest().identify());
            if (context.getRequest() != null) {
                one.setRequest(JSON.toJSONString(context.getRequest(), JSONWriter.Feature.WriteClassName));
            }
            if (context.getParams() != null) {
                one.setParams(JSON.toJSONString(context.getParams()));
            }
            if (context.getBill() != null) {
                one.setBill(JSON.toJSONString(context.getBill()));
            }
            if (context.getMatchedCostItem() != null) {
                List<String> itemCodeList = context.getMatchedCostItem().stream().map(item->item.getCode()).collect(Collectors.toList());
                one.setMatchedCostItem(JSON.toJSONString(itemCodeList));
            }
        });
    }

    public  BillingContext toContext(BillingContextDo dbO) {
        return ConvertUtil.to(dbO, BillingContext.class, (one) -> {
            one.setId(dbO.getId());
            if (!StringUtils.isBlank(dbO.getRequest())) {
                BillingRequest request = JSON.parseObject(dbO.getRequest(), BillingRequest.class);
                Request req = ConvertUtil.to(request, Request.class,r->{
                    r.setOrderInfo(request.getOrderInfo());
                });
                one.setRequest(req);
            }
            if (!StringUtils.isBlank(dbO.getParams())) {
                one.setParams(JSON.parseObject(dbO.getParams(), Map.class));
            }
            if (!StringUtils.isBlank(dbO.getBill())) {
                one.setBill(JSON.parseObject(dbO.getBill(), Bill.class));
            }
            if (!StringUtils.isBlank(dbO.getMatchedCostItem())) {
                List<String> itemCodeList = JSON.parseArray(dbO.getMatchedCostItem(), String.class);
                List<CostItemDo> dos = costItemRepository.listByCodeList(itemCodeList);
                List<CostItem> costItemList = dos.stream().map(d->factory.load(d)).collect(Collectors.toList());
                one.setMatchedCostItem(costItemList);
            }
        });
    }
}
