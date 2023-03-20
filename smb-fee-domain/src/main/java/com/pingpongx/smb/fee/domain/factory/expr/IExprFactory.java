package com.pingpongx.smb.fee.domain.factory.expr;

import com.pingpongx.smb.fee.api.dtos.expr.ExprDto;
import com.pingpongx.smb.fee.api.dtos.expr.FixDto;
import com.pingpongx.smb.fee.dal.repository.VariableDefRepository;
import com.pingpongx.smb.fee.domain.module.express.Expr;
import com.pingpongx.smb.fee.domain.module.express.Fixed;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;


public interface IExprFactory<Dto extends ExprDto> {
    Expr load( Dto dto);
}
