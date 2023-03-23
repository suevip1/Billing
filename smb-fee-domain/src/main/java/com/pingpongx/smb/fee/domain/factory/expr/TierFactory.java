package com.pingpongx.smb.fee.domain.factory.expr;

import com.pingpongx.smb.fee.api.dtos.expr.ExprDto;
import com.pingpongx.smb.fee.api.dtos.expr.MinDto;
import com.pingpongx.smb.fee.api.dtos.expr.NodeWithContidionDto;
import com.pingpongx.smb.fee.api.dtos.expr.TierDto;
import com.pingpongx.smb.fee.dal.dataobject.ConfiguredVariableDef;
import com.pingpongx.smb.fee.dal.repository.VariableDefRepository;
import com.pingpongx.smb.fee.domain.factory.variable.VariableFactory;
import com.pingpongx.smb.fee.domain.module.express.*;
import com.pingpongx.smb.metadata.VariableDef;
import com.pingpongx.smb.store.Codec;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component("Tier")
@RequiredArgsConstructor
public class TierFactory implements IExprFactory{
    @Autowired
    @Lazy
    ExprFactory exprFactory;
    private final VariableDefRepository variableDefRepository;

    @Override
    public Expr load(ExprDto dto) {
        TierDto d = (TierDto) dto;
        Tier e = new Tier();
        List<ExprWithCondition> list = d.getList().stream().map(n -> toDomain(n)).collect(Collectors.toList());
        e.setList(list);
        //TODO 分多次请求DB可以集中到一个计费参数准备阶段用in语句批量完成
        ConfiguredVariableDef def = variableDefRepository.getByCode(((TierDto) dto).getVarCode());
        VariableDef defDomain = VariableFactory.load(def);
        e.setRangeVar(defDomain);
        return e;
    }

    ExprWithCondition toDomain(NodeWithContidionDto dto){
        TierNode condition = new TierNode();
        condition.setCondition(dto.getCondition());
        condition.setExpr(exprFactory.load(dto.getExpr()));

        return condition;
    }
}
