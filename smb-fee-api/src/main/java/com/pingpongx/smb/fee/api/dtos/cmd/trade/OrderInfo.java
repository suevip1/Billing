package com.pingpongx.smb.fee.api.dtos.cmd.trade;

import com.pingpongx.smb.fee.api.dtos.cmd.OrderHolder;
import com.pingpongx.smb.fee.api.dtos.cmd.common.PayeeInfo;
import com.pingpongx.smb.fee.api.dtos.cmd.common.PayerInfo;
import lombok.Data;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

@Data
public class OrderInfo implements OrderHolder {
    private static final long serialVersionUID = 8061568762580326972L;
    /**
     * 币种信息
     */
    String sourceCurrency;
    String targetCurrency;

    /****
     * OrderPayer/OrderPayee 费用支付方
     */
    String feePayer;
    /**
     * 目标金额
     */
    BigDecimal amount;

    /**
     * 业务单 唯一定位坐标
     */
    String bizOrderId;
    String bizOrderType;

    /***
     * Did buClientId 具体数据
     */
    String subject;
    /***
     * Did / accid / Nid / buClientId
     */
    String subjectType;
    /***
     * 附言
     */
    private String remark;
    private String additionalInfo;
    private PayerInfo payerInfo;
    private PayeeInfo payeeInfo;
    /**
     * 拓展
     */
    private Map<String, Object> extraInfo;
    /**
     * 业务标
     */
    private List<String> bizTag;

    @Override
    public String valid() {
        if (bizOrderType == null){
            return "bizOrderType can't be null.";
        }
        if (bizOrderId == null){
            return "bizOrderId can't be null.";
        }
        if (payeeInfo == null){
            return "payeeInfo can't be null.";
        }
        if (payerInfo == null){
            return "payerInfo can't be null.";
        }
        if (amount == null){
            return "amount can't be null.";
        }
        if (sourceCurrency == null){
            return "sourceCurrency can't be null.";
        }
        if (targetCurrency == null){
            return "targetCurrency can't be null.";
        }
        return null ;
    }

    @Override
    public String getIdentify() {
        return bizOrderType+"-"+bizOrderId;
    }

}
