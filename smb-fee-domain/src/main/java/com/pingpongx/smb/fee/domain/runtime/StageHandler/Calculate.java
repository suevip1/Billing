package com.pingpongx.smb.fee.domain.runtime.StageHandler;

import com.pingpongx.smb.fee.dal.repository.BillingContextRepository;
import com.pingpongx.smb.fee.domain.module.CostItem;
import com.pingpongx.smb.fee.domain.module.event.CalculateCompleted;
import com.pingpongx.smb.fee.domain.module.event.MatchCompleted;
import com.pingpongx.smb.fee.domain.runtime.BillingContext;
import io.vavr.Tuple;
import io.vavr.Tuple2;
import lombok.RequiredArgsConstructor;
import org.springframework.context.ApplicationContext;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class Calculate {
    private final ApplicationContext applicationContext;
    @EventListener
    void calculate(MatchCompleted requestReceived) {
        BillingContext context = requestReceived.getContext();
        List<CostItem> costItemList = requestReceived.getContext().getMatchedCostItem();
        Map<String, BigDecimal> result = costItemList.stream().sorted(Comparator.comparingInt(CostItem::getPriority))
                .map(costItem -> Tuple.of(costItem.getCode(),costItem.getCalculateExpress().getCalculator().getCalculateResult(context))).collect(Collectors.toMap(Tuple2::_1,Tuple2::_2));
        context.setCalculateResult(result);
        CalculateCompleted calculateCompleted = new CalculateCompleted(context);
        applicationContext.publishEvent(calculateCompleted);
    }
}
