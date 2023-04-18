package com.pingpongx.smb.fee.domain.module.express;

import com.pingpongx.smb.fee.api.dtos.expr.AXpBDto;
import com.pingpongx.smb.fee.api.dtos.expr.ExprDto;
import com.pingpongx.smb.fee.domain.runtime.BillingContext;
import com.pingpongx.smb.metadata.Identified;
import com.pingpongx.smb.metadata.VariableDef;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.weaver.ast.Var;
import org.checkerframework.checker.units.qual.A;

import java.math.BigDecimal;

@Data
@Slf4j
public class AXpB implements BasicExpr, Calculator, Identified {
    BigDecimal a;
    BigDecimal b;

    VariableDef x;

    public static AXpB of(BigDecimal a, BigDecimal b, VariableDef x){
        AXpB ret = new AXpB();
        ret.setA(a);
        ret.setB(b);
        ret.setX(x);
        return ret;
    }

    @Override
    public String identify() {
        StringBuilder builder = new StringBuilder();
        builder.append(a).append("*").append(x.getCode()).append("+").append(b);
        return builder.toString();
    }

    @Override
    public Calculator fetchCalculator() {
        return this;
    }

    @Override
    public String getType() {
        return "AXpB";
    }

    @Override
    public ExprDto toDto() {
        AXpBDto dto = new AXpBDto();
        dto.setA(a.toString());
        dto.setB(b.toString());
        dto.setVarCode(x.getCode());
        return dto;
    }


    @Override
    public BigDecimal getCalculateResult(BillingContext context) {
        BigDecimal ret = context.getCache().get(this.identify());
        if (ret != null) {
            return ret;
        }
        String amount = x.extractor().doExtract(x, context);
        if (amount == null){
            throw new RuntimeException("variable amount not defined in namespace:"+context.getNameSpace());
        }
        BigDecimal xVal = new BigDecimal(amount);
        log.info("variable x is " + xVal);
        ret = xVal.multiply(a).add(b);
        log.info("result : " + ret);
        context.getCache().put(this.identify(), ret);
        return ret;
    }

}
