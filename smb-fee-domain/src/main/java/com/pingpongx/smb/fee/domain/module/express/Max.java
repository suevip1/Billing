package com.pingpongx.smb.fee.domain.module.express;

import com.pingpongx.smb.fee.api.dtos.expr.ExprDto;
import com.pingpongx.smb.fee.api.dtos.expr.MaxDto;
import com.pingpongx.smb.fee.domain.runtime.BillingContext;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class Max implements ExprCollection,Calculator{
    List<Expr> list = new ArrayList<>();

    @Override
    public List<Expr> asList() {
        return list;
    }

    @Override
    public String identify() {
        return "Max("+list.stream().map(Expr::identify).collect(Collectors.joining(","))+")";
    }

    @Override
    public Calculator getCalculator() {
        return this;
    }

    @Override
    public String getType() {
        return "Max";
    }

    @Override
    public ExprDto toDto() {
        MaxDto maxDto = new MaxDto();
        List<ExprDto> dtoList = list.stream().map(expr -> expr.toDto()).collect(Collectors.toList());
        maxDto.setList(dtoList);
        return maxDto;
    }


    @Override
    public BigDecimal getCalculateResult(BillingContext context) {
        BigDecimal ret = context.getCache().get(this.identify());
        if (ret !=null ){
            return ret;
        }
        ret = list.stream().map(expr -> expr.getCalculator().getCalculateResult(context)).max(BigDecimal::compareTo)
                .orElseThrow(()-> new RuntimeException("Min value calculate error. list is empty."));
        context.getCache().put(this.identify(),ret);
        return ret;
    }
    
}
