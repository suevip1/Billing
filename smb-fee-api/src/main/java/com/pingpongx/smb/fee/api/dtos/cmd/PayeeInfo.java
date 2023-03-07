package com.pingpongx.smb.fee.api.dtos.cmd;

import com.pingpongx.smb.payment.api.req.ApplyPayRequest;
import lombok.Data;

import java.math.BigDecimal;
import java.util.Map;

@Data
public class PayeeInfo {
    /**
     * 可空
     */
    private String clientIdType;
    /**
     * 可空
     */
    private String clientId;
    private String payeeAccountNo;
    private String payeeName;
    private String bankName;
}
