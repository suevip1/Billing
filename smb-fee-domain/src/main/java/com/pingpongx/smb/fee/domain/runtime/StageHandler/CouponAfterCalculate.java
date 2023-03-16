package com.pingpongx.smb.fee.domain.runtime.StageHandler;

import com.pingpongx.smb.fee.dal.dataobject.BillingContextDo;
import com.pingpongx.smb.fee.dal.repository.BillingContextRepository;
import com.pingpongx.smb.fee.domain.convert.runtime.BillingContextConvert;
import com.pingpongx.smb.fee.domain.module.event.BillingRequestReceived;
import com.pingpongx.smb.fee.domain.module.event.CalculateCompleted;
import com.pingpongx.smb.fee.domain.runtime.BillingContext;
import lombok.RequiredArgsConstructor;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Service;

import java.util.Map;
import java.util.concurrent.CompletableFuture;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class CouponAfterCalculate {
    private final BillingContextRepository repository;

    @EventListener
    void persistenceRequestReceived(CalculateCompleted calculateCompleted) {
        BillingContext context = calculateCompleted.getContext();
        context.getBill();
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
