package com.pingpongx.smb.fee.domain.factory.expr;

import com.pingpongx.smb.fee.api.dtos.expr.AXpBDto;
import com.pingpongx.smb.fee.api.dtos.expr.ExprDto;
import com.pingpongx.smb.fee.api.dtos.expr.FixDto;
import com.pingpongx.smb.fee.dal.dataobject.ConfiguredVariableDef;
import com.pingpongx.smb.fee.dal.repository.VariableDefRepository;
import com.pingpongx.smb.fee.domain.factory.variable.VariableFactory;
import com.pingpongx.smb.fee.domain.module.express.AXpB;
import com.pingpongx.smb.fee.domain.module.express.Expr;
import com.pingpongx.smb.fee.domain.module.express.Fixed;
import com.pingpongx.smb.metadata.VariableDef;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;

@Component
public class FixFactory implements IExprFactory{

    @Override
    public Expr load(ExprDto dto) {
        FixDto fixDto = (FixDto)dto;
        Fixed fix = new Fixed();
        fix.setFix(new BigDecimal(fixDto.getFix()));
        return fix;
    }
}
