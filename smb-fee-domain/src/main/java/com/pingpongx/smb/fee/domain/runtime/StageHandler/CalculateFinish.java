package com.pingpongx.smb.fee.domain.runtime.StageHandler;

import com.pingpongx.smb.fee.domain.module.event.CouponParamUpdated;
import com.pingpongx.smb.fee.domain.module.event.Finished;
import com.pingpongx.smb.fee.domain.module.event.MatchParamCompleted;
import com.pingpongx.smb.fee.domain.runtime.BillingContext;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Service;

import java.util.Map;
import java.util.concurrent.CompletableFuture;
import java.util.stream.Collectors;

@Service
public class CalculateFinish extends BizFlowNode{
    @EventListener
    void couponParamFix(Finished finish) {
        BillingContext context = finish.getContext();
        CompletableFuture<BillingContext> future = context.getFuture();
        if (future!=null&&context.getBill().getFailedReasons().isEmpty()){
            future.complete(context);
        }else if (future!=null&&context.getBill().getFailedReasons().isEmpty()){
            Map<String , String> reasons =  context.getBill().getFailedReasons();
            RuntimeException exception = new RuntimeException(reasons.values().stream().collect(Collectors.joining("\n")));
            future.completeExceptionally(exception);
        }

    }
}
