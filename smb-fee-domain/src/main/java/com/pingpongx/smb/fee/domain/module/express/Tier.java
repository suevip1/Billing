package com.pingpongx.smb.fee.domain.module.express;

import com.pingpongx.smb.export.module.persistance.Range;
import com.pingpongx.smb.fee.api.dtos.expr.ExprDto;
import com.pingpongx.smb.fee.api.dtos.expr.NodeWithContidionDto;
import com.pingpongx.smb.fee.api.dtos.expr.TierDto;
import com.pingpongx.smb.fee.domain.runtime.BillingContext;

import java.math.BigDecimal;
import java.util.*;
import java.util.stream.Collectors;

public class Tier implements TierMap,Calculator{

    List<ExprWithCondition> list = new ArrayList<>();

    @Override
    public Map<Range, Expr> asMap() {
        return list.stream().collect(Collectors.toMap(ExprWithCondition::getRange,ExprWithCondition::getExpress));
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
                .map(n->n.toDto()).collect(Collectors.toList());
        tierDto.setList(nodeList);
        return tierDto;
    }

    @Override
    public BigDecimal getCalculateResult(BillingContext context) {
        //阶梯子元素必须先于阶梯计算，匹配子元素有且仅有一个
        return list.stream()
                .map(ExprWithCondition::getExprIdentify)
                .map(key->context.getCache().get(key))
                .filter(Objects::nonNull)
                .findFirst()
                .orElseThrow(()->new RuntimeException("Tier must calculate at end of other calculator,pls modify the collection priority."));
    }

    public void setList(List<ExprWithCondition> list) {
        this.list = list;
    }
}
