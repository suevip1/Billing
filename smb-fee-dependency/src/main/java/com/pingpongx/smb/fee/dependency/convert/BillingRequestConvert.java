package com.pingpongx.smb.fee.dependency.convert;

import com.alibaba.fastjson2.JSON;
import com.pingpongx.smb.fee.api.dtos.cmd.BillingRequest;
import com.pingpongx.smb.fee.api.dtos.cmd.CouponInfo;
import com.pingpongx.smb.fee.api.dtos.cmd.OrderInfo;
import com.pingpongx.smb.fee.dal.dataobject.BillingRequestDo;

public class BillingRequestConvert {
    public static BillingRequestDo toDo(BillingRequest request) {
        return ConvertUtil.toDo(request, BillingRequestDo.class, (one) -> {
            one.setBizOrderId(request.getOrder().getBizOrderId());
            one.setBizOrderType(request.getOrder().getBizOrderType());
            one.setOrder(JSON.toJSONString(request.getOrder()));
            one.setCouponList(JSON.toJSONString(request.getCouponList()));
            one.setRepeat(request.identify());
        });
    }

    public static BillingRequest toRequest(BillingRequestDo request) {
        return ConvertUtil.to(request, BillingRequest.class, (one) -> {
            one.setOrder(JSON.parseObject(request.getOrder(), OrderInfo.class));
            one.setCouponList(JSON.parseArray(request.getCouponList(), CouponInfo.class));
        });
    }
}
