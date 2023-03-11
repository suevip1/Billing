package com.pingpongx.smb.fee.domain.factory.expr;

import com.pingpongx.smb.fee.api.dtos.expr.AXpBDto;
import com.pingpongx.smb.fee.api.dtos.expr.ExprDto;
import com.pingpongx.smb.fee.dal.dataobject.ConfiguredVariableDef;
import com.pingpongx.smb.fee.dal.repository.VariableDefRepository;
import com.pingpongx.smb.fee.domain.factory.variable.VariableFactory;
import com.pingpongx.smb.fee.domain.module.express.AXpB;
import com.pingpongx.smb.fee.domain.module.express.Expr;
import com.pingpongx.smb.metadata.VariableDef;
import org.checkerframework.checker.units.qual.A;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
@Component
public class AXpBFactory {
    VariableDefRepository variableDefRepository;
    public  AXpB load(AXpBDto dto){
        AXpB aXpB = new AXpB();
        aXpB.setA(new BigDecimal(dto.getA()));
        aXpB.setB(new BigDecimal(dto.getB()));
        ConfiguredVariableDef def = variableDefRepository.getByCode(dto.getVarCode());
        VariableDef defDomain = VariableFactory.load(def);
        aXpB.setX(defDomain);
        return aXpB;
    }
}
