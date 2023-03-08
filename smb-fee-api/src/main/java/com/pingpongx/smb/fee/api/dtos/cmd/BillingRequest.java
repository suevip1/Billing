package com.pingpongx.smb.fee.api.dtos.cmd;

import com.pingpongx.smb.export.module.Identified;
import io.vavr.collection.Tree;
import lombok.Builder;
import lombok.Data;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Data
public class BillingRequest implements Serializable {
    /***
     * 计费时间
     */
    Long billingTime;
    /***
     * Did buClientId 具体数据
     */
    String subject;
    /***
     * Did / accid / Nid / buClientId
     */
    String subjectType;
    /***
     * 汇率列表，目标货种：原币种  目标币种：美元  原币种：美元
     */
    Map<String, BigDecimal> fxRate;
    /**
     * 用于幂等
     */
    Long fxRateId;
    OrderInfo order;
    /**
     * 来源系统 B2B FM Dispatch Common
     */
    String bizLine;
    /**
     * 来源应用
     */
    String sourceApp;
    /**
     * 优惠券
     */
    List<CouponInfo> couponList;

    public String repeatKey(){
        String split = ":";
        StringBuilder builder = new StringBuilder();
        builder.append(order.getBizOrderType()).append(split);
        builder.append(order.getBizOrderId()).append(split);
        builder.append(fxRateId).append(split);
        String couponRepeat = couponList.stream().map(couponInfo -> couponInfo.repeatKey()).collect(Collectors.joining(","));
        builder.append(couponRepeat);
        return builder.toString();
    }

}
