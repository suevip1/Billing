package com.pingpongx.smb.fee.domain.factory.expr;

import com.pingpongx.smb.fee.api.dtos.expr.AXpBDto;
import com.pingpongx.smb.fee.api.dtos.expr.ExprDto;
import com.pingpongx.smb.fee.dal.dataobject.ConfiguredVariableDef;
import com.pingpongx.smb.fee.dal.repository.VariableDefRepository;
import com.pingpongx.smb.fee.domain.factory.variable.VariableFactory;
import com.pingpongx.smb.fee.domain.module.express.AXpB;
import com.pingpongx.smb.fee.domain.module.express.Expr;
import com.pingpongx.smb.metadata.VariableDef;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;

@Component("AXpB")
@RequiredArgsConstructor
public class AXpBFactory implements IExprFactory {
    @Override
    public Expr load(ExprDto d) {
        AXpBDto dto = (AXpBDto) d;
        AXpB aXpB = new AXpB();
        aXpB.setA(new BigDecimal(dto.getA()));
        aXpB.setB(new BigDecimal(dto.getB()));
        aXpB.setX(((AXpBDto) d).getVarCode());
        return aXpB;
    }
}
