package com.pingpongx.smb.fee.domain.module.event;

import com.pingpongx.smb.fee.domain.runtime.BillingContext;

public abstract class AbstractStage implements BillingStage{

    BillingContext context;

    @Override
    public String getStageCode() {
        return this.getClass().getSimpleName();
    }

    @Override
    public BillingContext getContext() {
        return context;
    }

    public AbstractStage(BillingContext context){
        this.context = context;
    }
}
