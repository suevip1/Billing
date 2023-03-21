package com.pingpongx.smb.fee.domain.factory.expr;

import com.pingpongx.smb.fee.api.dtos.expr.ExprDto;
import com.pingpongx.smb.fee.api.dtos.expr.MaxDto;
import com.pingpongx.smb.fee.api.dtos.expr.MinDto;
import com.pingpongx.smb.fee.domain.module.express.Expr;
import com.pingpongx.smb.fee.domain.module.express.Max;
import com.pingpongx.smb.fee.domain.module.express.Min;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component("Max")
public class MaxFactory implements IExprFactory{
    @Autowired
    ExprFactory exprFactory;
    @Override
    public Expr load(ExprDto dto) {
        MaxDto d = (MaxDto)dto;
        Max e = new Max();
        List<Expr> list = d.getList().stream().map(exprDto -> exprFactory.load(exprDto)).collect(Collectors.toList());
        e.setList(list);
        return e;
    }
}
