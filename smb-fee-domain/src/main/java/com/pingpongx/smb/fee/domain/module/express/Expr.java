package com.pingpongx.smb.fee.domain.module.express;

import com.pingpongx.smb.fee.api.dtos.expr.ExprDto;
import com.pingpongx.smb.metadata.Identified;

public interface Expr extends Identified {
    Calculator getCalculator();
    String getType();

    ExprDto toDto();

}
