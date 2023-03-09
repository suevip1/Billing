package com.pingpongx.smb.fee.domain.module.express;

import com.pingpongx.smb.fee.domain.runtime.BillingContext;

import java.math.BigDecimal;

public interface Calculator {
    BigDecimal getCalculateResult(BillingContext context);
}
