package com.pingpongx.smb.fee.domain.entity.express;

import org.joda.money.Money;

import java.math.BigDecimal;

public class Fixed extends AXpB{
    Fixed(BigDecimal fixed){
        this.a = new BigDecimal(0L);
        this.b = fixed;
    }

    @Override
    public String identity() {
        return Fixed.class.getSimpleName();
    }
}
