package com.pingpongx.smb.fee.api.dtos.cmd.child;

import lombok.Data;

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
