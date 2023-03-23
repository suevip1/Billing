package com.pingpongx.smb.fee.domain.module.express;

import com.pingpongx.smb.common.segtree.SegTree;
import com.pingpongx.smb.export.module.Rule;
import com.pingpongx.smb.export.module.persistance.Range;


import java.util.List;
import java.util.Map;

public interface TierMap extends Expr {
    Map<Range,Expr> asMap();
    SegTree<List<Expr>> asSegTree();
}
