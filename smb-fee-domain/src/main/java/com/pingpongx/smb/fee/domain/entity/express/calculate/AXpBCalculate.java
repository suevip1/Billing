package com.pingpongx.smb.fee.domain.entity.express.calculate;

import com.pingpongx.smb.fee.domain.entity.express.AXpB;
import com.pingpongx.smb.fee.domain.entity.express.Calculator;
import lombok.Builder;
import org.joda.money.Money;

import java.math.RoundingMode;
import java.util.List;

@Builder
public class AXpBCalculate implements Calculator<AXpB> {

    AXpB def;
    Money input;


    @Override
    public Money getCalculateResult() {
        return input.multipliedBy(def.getA(), RoundingMode.HALF_UP).plus(def.getB());
    }

    @Override
    public void setInput(List<Money> input) {
        if (input == null || input.isEmpty()){
            throw new RuntimeException("input is empty. ");
        }
        this.input = input.get(0);
    }
}
