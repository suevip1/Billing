package com.pingpongx.smb.fee.server.utils.convert;

import com.aliyun.openservices.shade.com.alibaba.fastjson.JSON;
import com.pingpongx.smb.fee.api.dtos.cmd.BillingRequest;
import com.pingpongx.smb.fee.api.dtos.cmd.CouponInfo;
import com.pingpongx.smb.fee.api.dtos.cmd.OrderInfo;
import com.pingpongx.smb.fee.dal.dataobject.BillingRequestDo;
import com.pingpongx.smb.fee.server.utils.ConvertUtil;

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
        return ConvertUtil.toDto(request, BillingRequest.class, (one) -> {
            one.setOrder(JSON.parseObject(request.getOrder(), OrderInfo.class));
            one.setCouponList(JSON.parseArray(request.getCouponList(), CouponInfo.class));
        });
    }
}
