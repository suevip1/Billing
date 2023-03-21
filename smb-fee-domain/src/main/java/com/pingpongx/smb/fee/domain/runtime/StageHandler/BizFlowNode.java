package com.pingpongx.smb.fee.domain.runtime.StageHandler;

import com.pingpongx.smb.fee.api.dtos.resp.Bill;
import com.pingpongx.smb.fee.domain.module.event.CalculateFailed;
import com.pingpongx.smb.fee.domain.runtime.BillingContext;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;

public abstract class BizFlowNode {
    @Autowired
    ApplicationContext applicationContext;

    void handleException(BillingContext context,Exception e){
        Bill bill = new Bill();
        bill.getFailedReasons().put(this.getClass().getSimpleName(),e.getMessage());
        context.setBill(bill);
        context.getFuture().completeExceptionally(e);
        applicationContext.publishEvent(new CalculateFailed(context));
    }
}
