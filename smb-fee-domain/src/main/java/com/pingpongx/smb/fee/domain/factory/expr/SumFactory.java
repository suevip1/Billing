package com.pingpongx.smb.fee.domain.factory.expr;

import com.pingpongx.smb.fee.api.dtos.expr.ExprDto;
import com.pingpongx.smb.fee.api.dtos.expr.MinDto;
import com.pingpongx.smb.fee.api.dtos.expr.SumDto;
import com.pingpongx.smb.fee.domain.module.express.Expr;
import com.pingpongx.smb.fee.domain.module.express.Min;
import com.pingpongx.smb.fee.domain.module.express.Sum;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component("Sum")
public class SumFactory implements IExprFactory{
    @Autowired
    @Lazy
    ExprFactory exprFactory;
    @Override
    public Expr load(ExprDto dto) {
        SumDto d = (SumDto)dto;
        Sum e = new Sum();
        List<Expr> list = d.getList().stream().map(exprDto -> exprFactory.load(exprDto)).collect(Collectors.toList());
        e.setList(list);
        return e;
    }
}
