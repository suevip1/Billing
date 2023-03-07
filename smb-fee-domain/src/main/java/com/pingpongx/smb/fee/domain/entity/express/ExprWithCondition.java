package com.pingpongx.smb.fee.domain.entity.express;

import org.joda.money.Money;
import com.pingpongx.smb.export.module.Rule;

public interface ExprWithCondition {
    Rule getCondition();
    Expr getExpress();
}
