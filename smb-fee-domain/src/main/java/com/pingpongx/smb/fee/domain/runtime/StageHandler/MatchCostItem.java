package com.pingpongx.smb.fee.domain.runtime.StageHandler;

import com.pingpongx.smb.export.globle.Engine;
import com.pingpongx.smb.export.module.MatchResult;
import com.pingpongx.smb.fee.domain.module.CostItem;
import com.pingpongx.smb.fee.domain.module.Request;
import com.pingpongx.smb.fee.domain.module.event.MatchCompleted;
import com.pingpongx.smb.fee.domain.module.event.MatchParamCompleted;
import com.pingpongx.smb.fee.domain.rules.CostItemHolder;
import com.pingpongx.smb.fee.domain.rules.Engines;
import com.pingpongx.smb.fee.domain.rules.MatchContext;
import com.pingpongx.smb.fee.domain.runtime.BillingContext;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Slf4j
public class MatchCostItem extends BizFlowNode{
    private final Engines engines;

    @EventListener
    void doMatch(MatchParamCompleted requestReceived) {
        BillingContext context = requestReceived.getContext();
        Request request = requestReceived.getContext().getRequest();
        try{
            Engine engine = engines.getEngine(request.getCostNodeCode());
            if (engine == null){
                log.warn("Cost Node : "+request.getCostNodeCode()+", has no cost item in it.");
                context.setMatchedCostItem(new ArrayList<>());
                MatchCompleted event = new MatchCompleted(context);
                applicationContext.publishEvent(event);
                return;
            }
            MatchResult result = engine.match(MatchContext.class.getSimpleName(), context.getParams());
            List<CostItem> costItemList = result.getMatchedData().stream().map(ruleHandler -> (CostItemHolder) ruleHandler).map(costItemHolder -> costItemHolder.getCostItem()).collect(Collectors.toList());
            context.setMatchedCostItem(costItemList);
            MatchCompleted event = new MatchCompleted(context);
            applicationContext.publishEvent(event);
        }catch (Exception e){
            handleException(context,e);
            log.error("error when match cost item.",e);
        }

    }
}
