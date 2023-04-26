package com.pingpongx.smb.fee.api.dtos.cmd;

import com.pingpongx.smb.export.module.Identified;

public interface OrderHolder extends Identified<String> {
    String valid();

    String getBizOrderId();

    String getBizOrderType();
}
