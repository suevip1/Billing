package com.pingpongx.smb.fee.domain.module.express;

import com.pingpongx.smb.export.module.persistance.Range;
import com.pingpongx.smb.fee.api.dtos.expr.NodeWithContidionDto;

public interface ExprWithCondition {
    Range getRange();
    Expr getExpress();
    String getExprIdentify();
    NodeWithContidionDto toDto();
}
