package com.pingpongx.smb.fee.domain.module.express;

import java.util.List;

public interface ComplexExpr extends Expr{
    void setInputs(List<Expr> exprList);
}
