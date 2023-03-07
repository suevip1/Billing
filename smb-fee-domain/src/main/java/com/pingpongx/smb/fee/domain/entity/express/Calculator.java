package com.pingpongx.smb.fee.domain.entity.express;

import org.joda.money.Money;

import java.util.List;

public interface Calculator<T extends Expr> {
    Money getCalculateResult();
    void setInput(List<Money> input);
}
