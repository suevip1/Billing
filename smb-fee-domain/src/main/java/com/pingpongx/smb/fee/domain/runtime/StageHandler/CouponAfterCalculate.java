package com.pingpongx.smb.fee.domain.runtime.StageHandler;

import com.pingpongx.smb.fee.dal.dataobject.BillingContextDo;
import com.pingpongx.smb.fee.dal.repository.BillingContextRepository;
import com.pingpongx.smb.fee.domain.convert.runtime.BillingContextConvert;
import com.pingpongx.smb.fee.domain.module.event.BillingRequestReceived;
import com.pingpongx.smb.fee.domain.module.event.CalculateCompleted;
import com.pingpongx.smb.fee.domain.module.event.CouponParamUpdated;
import com.pingpongx.smb.fee.domain.module.event.Finished;
import com.pingpongx.smb.fee.domain.runtime.BillingContext;
import lombok.RequiredArgsConstructor;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Service;

import java.util.Map;
import java.util.concurrent.CompletableFuture;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class CouponAfterCalculate extends BizFlowNode {
    private final BillingContextRepository repository;

    @EventListener
    void couponResultFix(CalculateCompleted calculateCompleted) {
        BillingContext context = calculateCompleted.getContext();
        try{
            Finished finished = new Finished(context);
            applicationContext.publishEvent(finished);
        }catch (Exception e){
            handleException(context,e);
        }

    }
}
