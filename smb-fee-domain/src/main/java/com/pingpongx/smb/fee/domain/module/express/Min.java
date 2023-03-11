package com.pingpongx.smb.fee.domain.module.express;

import com.pingpongx.smb.fee.api.dtos.expr.ExprDto;
import com.pingpongx.smb.fee.api.dtos.expr.MaxDto;
import com.pingpongx.smb.fee.api.dtos.expr.MinDto;
import com.pingpongx.smb.fee.domain.runtime.BillingContext;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class Min implements ExprCollection,Calculator{
    List<Expr> list = new ArrayList<>();


    @Override
    public String identify() {
        return this.getClass().getSimpleName();
    }

    @Override
    public Calculator getCalculator() {
        return this;
    }

    @Override
    public String getType() {
        return "Min";
    }

    @Override
    public ExprDto toDto() {
        MinDto minDto = new MinDto();
        List<ExprDto> dtoList = list.stream().map(expr -> expr.toDto()).collect(Collectors.toList());
        minDto.setList(dtoList);
        return minDto;
    }

    @Override
    public List<Expr> asList() {
        return list;
    }

    @Override
    public BigDecimal getCalculateResult(BillingContext context) {
        BigDecimal ret = context.getCache().get(this.identify());
        if (ret !=null ){
            return ret;
        }
        ret = list.stream().map(expr -> expr.getCalculator().getCalculateResult(context)).min(BigDecimal::compareTo)
                .orElseThrow(()-> new RuntimeException("Min value calculate error. list is empty."));
        context.getCache().put(this.identify(),ret);
        return ret;
    }

}
