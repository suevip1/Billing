package com.pingpongx.smb.fee.domain.module.express;

import com.pingpongx.smb.export.module.Rule;
import com.pingpongx.smb.export.module.persistance.Range;


import java.util.Map;

public interface TierMap extends Expr {
    Map<Range,Expr> asMap();
}
