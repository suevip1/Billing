package com.pingpongx.smb.fee.domain.module.express;

import com.pingpongx.smb.export.module.Rule;

import java.util.Map;

public interface ExprMap extends Expr {
    Map<Rule,Expr> asMap();
}
