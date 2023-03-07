package com.pingpongx.smb.fee.domain.entity.express;

import org.joda.money.Money;

import java.util.List;

public interface ExprCollection extends Expr {
    List<Expr> asList();
}
