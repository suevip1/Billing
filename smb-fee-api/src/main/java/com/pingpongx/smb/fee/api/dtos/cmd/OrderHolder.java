package com.pingpongx.smb.fee.api.dtos.cmd;


import com.pingpongx.smb.metadata.Identified;

import java.math.BigDecimal;

public interface OrderHolder extends Identified {
    String valid();

    String getBizOrderId();

    String getBizOrderType();

    String getFeePayer();

    String getSourceCurrency();

    String getTargetCurrency();

    BigDecimal getAmount();
}
