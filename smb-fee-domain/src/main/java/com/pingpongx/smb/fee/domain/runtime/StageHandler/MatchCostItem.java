package com.pingpongx.smb.fee.domain.runtime.StageHandler;

import com.pingpongx.smb.export.globle.Engine;
import com.pingpongx.smb.export.module.MatchResult;
import com.pingpongx.smb.fee.api.dtos.cmd.BillingRequest;
import com.pingpongx.smb.fee.domain.module.CostItem;
import com.pingpongx.smb.fee.domain.module.event.MatchCompleted;
import com.pingpongx.smb.fee.domain.module.event.MatchParamCompleted;
import com.pingpongx.smb.fee.domain.rules.CostItemHolder;
import com.pingpongx.smb.fee.domain.rules.Engines;
import com.pingpongx.smb.fee.domain.runtime.BillingContext;
import lombok.RequiredArgsConstructor;
import org.springframework.context.ApplicationContext;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class MatchCostItem {
    private final Engines engines;
    private final ApplicationContext applicationContext;

    @EventListener
    void doMatch(MatchParamCompleted requestReceived) {
        BillingContext context = requestReceived.getContext();
        BillingRequest request = requestReceived.getContext().getRequest();
        Engine engine = engines.getEngine(request.getCostNodeCode());
        MatchResult result = engine.match(BillingContext.class.getSimpleName(), context);
        List<CostItem> costItemList = result.getMatchedData().stream().map(ruleHandler -> (CostItemHolder) ruleHandler).map(costItemHolder -> costItemHolder.getCostItem()).collect(Collectors.toList());
        context.setMatchedCostItem(costItemList);
        MatchCompleted event = new MatchCompleted(context);
        applicationContext.publishEvent(event);
    }
}
