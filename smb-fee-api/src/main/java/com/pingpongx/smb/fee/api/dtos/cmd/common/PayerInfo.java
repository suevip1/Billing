package com.pingpongx.smb.fee.api.dtos.cmd.common;

import lombok.Data;

@Data
public class PayerInfo {
    private String clientIdType;
    private String clientId;
    private String payerName;
    private String reservedPhone;
    private String certificateNumber;
}
