package com.pingpongx.smb.fee.api.dtos.cmd;

import lombok.Data;

import java.math.BigDecimal;
@Data
public class CouponInfo {
    BigDecimal usdCredit;
    String templateId;
}
