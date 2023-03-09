package com.pingpongx.smb.fee.domain.module.express;

import com.pingpongx.smb.export.module.Rule;
import com.pingpongx.smb.fee.domain.runtime.BillingContext;

import java.math.BigDecimal;
import java.util.*;
import java.util.stream.Collectors;

public class Tier implements ExprMap,Calculator{

    List<ExprWithCondition> list = new ArrayList<>();

    @Override
    public Map<Rule, Expr> asMap() {
        return list.stream().collect(Collectors.toMap(ExprWithCondition::getCondition,ExprWithCondition::getExpress));
    }

    @Override
    public String identify() {
        return this.getClass().getSimpleName();
    }

    @Override
    public Calculator getCalculator() {
        //阶梯元素会被入引擎匹配，阶梯集合本身不会入引
        return this;
    }

    @Override
    public BigDecimal getCalculateResult(BillingContext context) {
        //阶梯子元素必须先于阶梯计算，匹配子元素有且仅有一个
        return list.stream()
                .map(ExprWithCondition::getExprIdentify)
                .map(key->context.getCache().get(key))
                .filter(Objects::nonNull)
                .findFirst()
                .orElseThrow(()->new RuntimeException("Tier must calculate at end of other calculator,pls modify the collection priority."));
    }
}
