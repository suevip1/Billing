package com.pingpongx.smb.fee.domain.entity.express;

import java.math.BigDecimal;
import java.math.RoundingMode;

import org.joda.money.Money;
public class AXpB implements BasicExpr {
    BigDecimal a;
    BigDecimal b;

    @Override
    public String identity() {
        return AXpB.class.getSimpleName();
    }

    public BigDecimal getA() {
        return a;
    }

    public void setA(BigDecimal a) {
        this.a = a;
    }

    public BigDecimal getB() {
        return b;
    }

    public void setB(BigDecimal b) {
        this.b = b;
    }
}
