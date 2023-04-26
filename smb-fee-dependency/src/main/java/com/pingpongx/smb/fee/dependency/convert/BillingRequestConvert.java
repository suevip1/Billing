package com.pingpongx.smb.fee.dependency.convert;

import com.alibaba.fastjson2.JSON;
import com.alibaba.fastjson2.JSONWriter;
import com.pingpongx.smb.fee.api.dtos.cmd.BillingRequest;
import com.pingpongx.smb.fee.api.dtos.cmd.CouponInfo;
import com.pingpongx.smb.fee.api.dtos.cmd.OrderHolder;
import com.pingpongx.smb.fee.api.dtos.cmd.OrderInfo;
import com.pingpongx.smb.fee.dal.dataobject.BillingRequestDo;

import java.util.Map;

public class BillingRequestConvert {
    public static BillingRequestDo toDo(BillingRequest request) {
        return ConvertUtil.toDo(request, BillingRequestDo.class, (one) -> {
            one.setBizOrderId(request.getOrderInfo().getBizOrderId());
            one.setBizOrderType(request.getOrderInfo().getBizOrderType());
            one.setOrderInfo(JSON.toJSONString(request.getOrderInfo()));
            one.setCouponList(JSON.toJSONString(request.getCouponList()));
            one.setFxRate(JSON.toJSONString(request.getFxRate(), JSONWriter.Feature.WriteClassName));
            one.setRepeatKey(request.identify());
        });
    }

    public static BillingRequest toRequest(BillingRequestDo request) {
        return ConvertUtil.to(request, BillingRequest.class, (one) -> {
            one.setOrderInfo(JSON.parseObject(request.getOrderInfo(), OrderInfo.class));
            one.setCouponList(JSON.parseArray(request.getCouponList(), CouponInfo.class));
            one.setFxRate(JSON.parseObject(request.getFxRate(), Map.class));
        });
    }
}
