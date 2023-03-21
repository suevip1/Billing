package com.pingpongx.smb.fee.domain.factory.expr;

import com.pingpongx.smb.fee.api.dtos.expr.ExprDto;
import com.pingpongx.smb.fee.api.dtos.expr.MinDto;
import com.pingpongx.smb.fee.api.dtos.expr.NodeWithContidionDto;
import com.pingpongx.smb.fee.api.dtos.expr.TierDto;
import com.pingpongx.smb.fee.domain.module.express.*;
import com.pingpongx.smb.store.Codec;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component("Tier")
public class TierFactory implements IExprFactory{
    @Autowired
    ExprFactory exprFactory;

    @Override
    public Expr load(ExprDto dto) {
        TierDto d = (TierDto) dto;
        Tier e = new Tier();
        List<ExprWithCondition> list = d.getList().stream().map(n -> toDomain(n)).collect(Collectors.toList());
        e.setList(list);
        return e;
    }

    ExprWithCondition toDomain(NodeWithContidionDto dto){
        TierNode condition = new TierNode();
        condition.setCondition(Codec.buildRule(dto.getCondition()));
        condition.setExpr(exprFactory.load(dto.getExpr()));
        return condition;
    }
}
