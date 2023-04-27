package com.pingpongx.smb.fee.dependency.convert;

import com.alibaba.fastjson2.JSON;
import com.alibaba.fastjson2.JSONWriter;
import com.pingpongx.smb.fee.api.dtos.cmd.common.CouponInfo;
import com.pingpongx.smb.fee.api.dtos.cmd.coupon.CouponOrderInfo;
import com.pingpongx.smb.fee.api.dtos.cmd.coupon.CouponRequest;
import com.pingpongx.smb.fee.api.dtos.cmd.trade.BillingRequest;
import com.pingpongx.smb.fee.api.dtos.cmd.trade.OrderInfo;
import com.pingpongx.smb.fee.dal.dataobject.BillingRequestDo;

import java.util.Map;

public class CouponRequestConvert {
    public static BillingRequestDo toDo(CouponRequest request) {
        return ConvertUtil.toDo(request, BillingRequestDo.class, (one) -> {
            one.setBizOrderId(request.getOrderInfo().getBizOrderId());
            one.setBizOrderType(request.getOrderInfo().getBizOrderType());
            one.setOrderInfo(JSON.toJSONString(request.getOrderInfo()));
            one.setCouponList(JSON.toJSONString(request.getCouponList()));
            one.setFxRate(JSON.toJSONString(request.getFxRate(), JSONWriter.Feature.WriteClassName));
            one.setRepeatKey(request.identify());
        });
    }

    public static CouponRequest toRequest(BillingRequestDo request) {
        return ConvertUtil.to(request, CouponRequest.class, (one) -> {
            one.setOrderInfo(JSON.parseObject(request.getOrderInfo(), CouponOrderInfo.class));
            one.setCouponList(JSON.parseArray(request.getCouponList(), CouponInfo.class));
            one.setFxRate(JSON.parseObject(request.getFxRate(), Map.class));
        });
    }
}
