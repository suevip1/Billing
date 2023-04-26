package com.pingpongx.smb.fee.api.dtos.cmd;

import com.pingpongx.smb.export.module.Identified;

import java.math.BigDecimal;

public interface OrderHolder extends Identified<String> {
    String valid();

    String getBizOrderId();

    String getBizOrderType();

    String getFeePayer();

    String getSourceCurrency();

    String getTargetCurrency();

    BigDecimal getAmount();
}
