package com.pingpongx.smb.fee.domain.module;

import com.pingpongx.smb.fee.api.dtos.cmd.BillingRequest;
import com.pingpongx.smb.fee.api.dtos.cmd.child.CouponInfo;
import com.pingpongx.smb.fee.api.dtos.cmd.OrderHolder;
import com.pingpongx.smb.fee.api.dtos.cmd.child.RateInfo;
import com.pingpongx.smb.fee.dependency.convert.ConvertUtil;
import lombok.Data;

import java.util.List;
import java.util.Map;
@Data
public class Request {

    private static final long serialVersionUID = 8061568762858026972L;

    /***
     * 计费时间
     */
    Long billingTime;
    /***
     * 计费节点
     */
    String costNodeCode;
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
    Map<String, RateInfo> fxRate;

    OrderHolder orderInfo;
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

    public String identify() {
        String split = ":";
        StringBuilder builder = new StringBuilder();
        builder.append(costNodeCode).append(split);
        builder.append(orderInfo.getIdentify()).append(split);
        return builder.toString();
    }


    public String valid(){
        if (this.costNodeCode == null){
            return "cost node code can't be null.";
        }
        if (this.billingTime == null){
            return "billingTime can't be null.";
        }
        if (orderInfo ==null){
            return "order info can't be null.";
        }
        if (sourceApp ==null){
            return "sourceApp info can't be null.";
        }
        return orderInfo.valid();
    }
    public  String getNameSpace(){
        return this.costNodeCode+"_"+this.sourceApp;
    }

    public static Request from(BillingRequest request){
        Request ret = ConvertUtil.to(request,Request.class,(r)->r.setOrderInfo(request.getOrderInfo()));
        return ret;
    }
}
