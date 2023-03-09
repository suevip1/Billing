package com.pingpongx.smb.fee.domain.module.express;

import com.pingpongx.smb.fee.domain.runtime.BillingContext;
import lombok.Data;

import java.math.BigDecimal;
@Data
public class Fixed implements BasicExpr,Calculator{
    BigDecimal fix;


    @Override
    public String identify() {
        StringBuilder builder = new StringBuilder();
        builder.append(fix);
        return builder.toString();
    }
    @Override
    public Calculator getCalculator() {
        return  this;
    }

    @Override
    public BigDecimal getCalculateResult(BillingContext context) {
        return fix;
    }
}
