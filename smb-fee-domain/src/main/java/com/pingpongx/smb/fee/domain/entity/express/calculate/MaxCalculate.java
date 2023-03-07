package com.pingpongx.smb.fee.domain.entity.express.calculate;

import com.pingpongx.smb.fee.domain.entity.express.Calculator;
import com.pingpongx.smb.fee.domain.entity.express.Max;
import com.pingpongx.smb.fee.domain.entity.express.Min;
import lombok.Builder;
import org.joda.money.Money;

import java.util.List;

@Builder
public class MaxCalculate implements Calculator<Min> {

    Max def;
    List<Money> input;


    @Override
    public Money getCalculateResult() {
        return input.stream().map(expr -> expr).max(Money::compareTo)
                .orElseThrow(()-> new RuntimeException("Min value calculate error. list is empty."));
    }

    @Override
    public void setInput(List<Money> input) {
        if (input == null || input.isEmpty()){
            throw new RuntimeException("input is empty. ");
        }
        this.input = input;
    }
}
