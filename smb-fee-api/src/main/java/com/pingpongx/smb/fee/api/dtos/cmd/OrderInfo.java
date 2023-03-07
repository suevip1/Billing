package com.pingpongx.smb.fee.api.dtos.cmd;

import com.pingpongx.smb.payment.api.req.ApplyPayRequest;
import lombok.Data;
import org.joda.money.Money;

import java.math.BigDecimal;
import java.util.Map;

@Data
public class OrderInfo {
    /**
     * 计费节点
     */
    String nodeCode;

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

    private String remark;
    private String additionalInfo;
    private PayerInfo payerInfo;
    private PayeeInfo payeeInfo;
    private Map<String, Object> extraInfo;
}
