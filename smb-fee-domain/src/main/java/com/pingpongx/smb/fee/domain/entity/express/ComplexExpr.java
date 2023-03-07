package com.pingpongx.smb.fee.domain.entity.express;

import org.joda.money.Money;

import java.util.List;

public interface ComplexExpr extends Expr{
    void setInputs(List<Expr> exprList);
}
