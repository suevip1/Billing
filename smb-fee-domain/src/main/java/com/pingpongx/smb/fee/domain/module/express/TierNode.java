package com.pingpongx.smb.fee.domain.module.express;

import com.pingpongx.smb.export.module.Rule;
import com.pingpongx.smb.export.module.persistance.RuleDto;
import com.pingpongx.smb.fee.api.dtos.expr.NodeWithContidionDto;

public class TierNode implements ExprWithCondition{

    Rule condition;
    Expr expr;
    @Override
    public Rule getCondition() {
        return condition;
    }

    @Override
    public Expr getExpress() {
        return expr;
    }

    @Override
    public String getExprIdentify() {
        return expr.identify();
    }

    @Override
    public NodeWithContidionDto toDto() {
        NodeWithContidionDto dto = new NodeWithContidionDto();
        RuleDto ruleDto = condition.expansion().toDto();
        dto.setCondition(ruleDto);
        dto.setExpr(expr.toDto());
        return dto;
    }

    public void setCondition(Rule condition) {
        this.condition = condition;
    }

    public Expr getExpr() {
        return expr;
    }

    public void setExpr(Expr expr) {
        this.expr = expr;
    }
}
