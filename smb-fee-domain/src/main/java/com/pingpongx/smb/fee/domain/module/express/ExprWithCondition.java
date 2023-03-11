package com.pingpongx.smb.fee.domain.module.express;

import com.pingpongx.smb.export.module.Rule;
import com.pingpongx.smb.fee.api.dtos.expr.NodeWithContidionDto;
import com.pingpongx.smb.fee.api.dtos.expr.TierDto;

public interface ExprWithCondition {
    Rule getCondition();
    Expr getExpress();
    String getExprIdentify();
    NodeWithContidionDto toDto();
}
