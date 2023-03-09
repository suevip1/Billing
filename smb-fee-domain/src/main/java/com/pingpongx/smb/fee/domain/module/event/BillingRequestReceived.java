package com.pingpongx.smb.fee.domain.module.event;

import com.pingpongx.smb.fee.domain.runtime.BillingContext;

public class BillingRequestReceived implements BillingStage {

    BillingContext context;

    @Override
    public String getStageCode() {
        return this.getClass().getSimpleName();
    }

    @Override
    public int getStagePriority() {
        return 0;
    }

    @Override
    public BillingContext getContext() {
        return context;
    }

    public BillingRequestReceived(BillingContext context){
        this.context = context;
    }
}
