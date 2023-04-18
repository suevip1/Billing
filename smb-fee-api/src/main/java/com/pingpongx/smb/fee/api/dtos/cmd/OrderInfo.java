package com.pingpongx.smb.fee.api.dtos.cmd;

import com.pingpongx.smb.trade.api.request.v2.TradeV2PayRequest;
import lombok.Data;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

@Data
public class OrderInfo implements Serializable {
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
}
