package com.pingpongx.smb.fee.domain.module.express;

import com.pingpongx.smb.fee.api.dtos.expr.ExprDto;
import com.pingpongx.smb.fee.api.dtos.expr.MinDto;
import com.pingpongx.smb.fee.api.dtos.expr.SumDto;
import com.pingpongx.smb.fee.domain.runtime.BillingContext;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class Sum implements ExprCollection,Calculator{
    List<Expr> list = new ArrayList<>();

    public static Sum of(Expr ... exprs){
        Sum min = new Sum();
        min.setList( Arrays.stream(exprs).collect(Collectors.toList()));
        return min;
    }
    @Override
    public String identify() {
        return this.getClass().getSimpleName()+":"+list.stream().map(expr -> expr.identify()).collect(Collectors.joining(","));
    }

    @Override
    public Calculator fetchCalculator() {
        return this;
    }

    @Override
    public String getType() {
        return "Sum";
    }

    @Override
    public ExprDto toDto() {
        SumDto minDto = new SumDto();
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
        ret = list.stream()
                .map(expr -> expr.fetchCalculator().getCalculateResult(context))
                .reduce((n1,n2)->n1.add(n2))
                .orElse(BigDecimal.ZERO);
        context.getCache().put(this.identify(),ret);
        return ret;
    }

    public void setList(List<Expr> list) {
        this.list = list;
    }
}
