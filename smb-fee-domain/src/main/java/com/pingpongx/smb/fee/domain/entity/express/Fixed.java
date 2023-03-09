package com.pingpongx.smb.fee.domain.entity.express;

import com.pingpongx.smb.fee.domain.runtime.BillingContext;
import com.pingpongx.smb.metadata.VariableDef;
import lombok.Data;
import org.joda.money.Money;

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
