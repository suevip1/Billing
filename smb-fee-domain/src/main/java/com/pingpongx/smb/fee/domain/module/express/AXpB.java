package com.pingpongx.smb.fee.domain.module.express;

import com.pingpongx.smb.fee.api.dtos.expr.AXpBDto;
import com.pingpongx.smb.fee.api.dtos.expr.ExprDto;
import com.pingpongx.smb.fee.domain.runtime.BillingContext;
import com.pingpongx.smb.metadata.Identified;
import com.pingpongx.smb.metadata.VariableDef;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;

import java.math.BigDecimal;

@Data
@Slf4j
public class AXpB implements BasicExpr, Calculator, Identified {
    BigDecimal a;
    BigDecimal b;

    VariableDef x;

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
        BigDecimal xVal = new BigDecimal(x.extractor().doExtract(x, context.getRequest()));
        log.info("variable x is " + xVal);
        ret = xVal.multiply(a).add(b);
        log.info("result : " + ret);
        context.getCache().put(this.identify(), ret);
        return ret;
    }

}
