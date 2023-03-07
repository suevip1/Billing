package com.pingpongx.smb.fee.api.dtos.cmd;

import com.pingpongx.smb.payment.api.req.ApplyPayRequest;
import lombok.Data;

import java.math.BigDecimal;
import java.util.Map;

@Data
public class PayerInfo {
    private String clientIdType;
    private String clientId;
    private String payerName;
    private String reservedPhone;
    private String certificateNumber;
}
