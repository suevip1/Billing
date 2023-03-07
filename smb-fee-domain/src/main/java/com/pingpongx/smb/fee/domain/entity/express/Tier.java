package com.pingpongx.smb.fee.domain.entity.express;

import com.pingpongx.smb.export.module.Rule;
import org.joda.money.Money;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class Tier implements ExprMap{

    List<ExprWithCondition> list = new ArrayList<>();

    @Override
    public Map<Rule, Expr> asMap() {
        return list.stream().collect(Collectors.toMap(ExprWithCondition::getCondition,ExprWithCondition::getExpress));
    }

    @Override
    public String identity() {
        return this.getClass().getSimpleName();
    }
//    @Override
//    public Money getCalculateResult() {
//        if (list.isEmpty()){
//            new RuntimeException("Min value calculate error. list is empty.");
//        }
//        if (list.size()>1){
//            new RuntimeException("Tire value calculate error. list is empty.");
//        }
//        return list.stream().map(expr -> expr.getCalculateResult()).min(Money::compareTo)
//                .orElseThrow(()-> new RuntimeException("Min value calculate error. list is empty."));
//    }

}
