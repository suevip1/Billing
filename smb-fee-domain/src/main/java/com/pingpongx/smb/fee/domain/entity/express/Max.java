package com.pingpongx.smb.fee.domain.entity.express;

import org.joda.money.Money;

import java.util.ArrayList;
import java.util.List;

public class Max implements ExprCollection{
    List<Expr> list = new ArrayList<>();

    @Override
    public List<Expr> asList() {
        return list;
    }

    @Override
    public String identity() {
        return this.getClass().getSimpleName();
    }


//    @Override
//    public Money getCalculateResult() {
//        return list.stream().map(expr -> expr.getCalculateResult()).max(Money::compareTo)
//                .orElseThrow(()-> new RuntimeException("Min value calculate error. list is empty."));
//    }

    
}
