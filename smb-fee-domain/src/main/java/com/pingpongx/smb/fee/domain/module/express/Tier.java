package com.pingpongx.smb.fee.domain.module.express;

import com.pingpongx.smb.common.segtree.SegTree;
import com.pingpongx.smb.common.segtree.Segment;
import com.pingpongx.smb.export.module.persistance.Range;
import com.pingpongx.smb.fee.api.dtos.expr.ExprDto;
import com.pingpongx.smb.fee.api.dtos.expr.NodeWithContidionDto;
import com.pingpongx.smb.fee.api.dtos.expr.TierDto;
import com.pingpongx.smb.fee.domain.runtime.BillingContext;
import com.pingpongx.smb.metadata.VariableDef;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Tier implements TierMap, Calculator {

    List<ExprWithCondition> list = new ArrayList<>();
    VariableDef rangeVar;

    @Override
    public Map<Range, Expr> asMap() {
        return list.stream().collect(Collectors.toMap(ExprWithCondition::getRange, ExprWithCondition::getExpress));
    }

    @Override
    public SegTree<List<Expr>> asSegTree() {
        SegTree<List<Expr>> segTree = new SegTree<>();
        list.stream().forEach(node -> {
            segTree.executeOperation(
                    Segment.of(node.getRange()),
                    Stream.of(node.getExpress()).collect(Collectors.toList()),
                    (n, o) -> {
                        if (n==null){
                            return o;
                        }
                        if (o == null){
                            return n;
                        }
                        n.addAll(o);
                        return n;
                    }
            );
        });
        return segTree;
    }

    @Override
    public String identify() {
        return this.getClass().getSimpleName();
    }

    @Override
    public Calculator fetchCalculator() {
        //阶梯元素会被入引擎匹配，阶梯集合本身不会入引
        return this;
    }

    @Override
    public String getType() {
        return "Tier";
    }

    @Override
    public ExprDto toDto() {
        TierDto tierDto = new TierDto();
        List<NodeWithContidionDto> nodeList = list.stream()
                .map(n -> n.toDto()).collect(Collectors.toList());
        tierDto.setList(nodeList);
        tierDto.setVarCode(this.rangeVar.getCode());
        return tierDto;
    }

    @Override
    public BigDecimal getCalculateResult(BillingContext context) {
        SegTree<List<Expr>> segTree = this.asSegTree();
        String varStr = rangeVar.extractor().doExtract(rangeVar,context);
        BigDecimal var = new BigDecimal(varStr);
        List<Expr> exprs = segTree.valuesOfPoint(var);
        if (exprs.size() == 0){
            throw new RuntimeException("config range can't cover the point: "+varStr);
        }
        if (exprs.size() >1){
            throw new RuntimeException("config range at the point: "+varStr+" has more then one result.");
        }
        return exprs.get(0).fetchCalculator().getCalculateResult(context);
    }

    public void setList(List<ExprWithCondition> list) {
        this.list = list;
    }

    public VariableDef getRangeVar() {
        return rangeVar;
    }

    public void setRangeVar(VariableDef rangeVar) {
        this.rangeVar = rangeVar;
    }
}
