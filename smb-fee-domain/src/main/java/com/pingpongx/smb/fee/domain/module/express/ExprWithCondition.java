package com.pingpongx.smb.fee.domain.module.express;

import com.pingpongx.smb.export.module.Rule;

public interface ExprWithCondition {
    Rule getCondition();
    Expr getExpress();
    String getExprIdentify();
}
