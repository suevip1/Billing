package com.pingpongx.smb.fee.domain.entity.express;

import com.pingpongx.smb.fee.domain.runtime.BillingContext;
import org.joda.money.Money;

import java.math.BigDecimal;
import java.util.List;

public interface Calculator {
    BigDecimal getCalculateResult(BillingContext context);
}
