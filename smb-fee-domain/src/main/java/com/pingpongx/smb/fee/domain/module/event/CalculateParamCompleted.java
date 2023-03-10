package com.pingpongx.smb.fee.domain.module.event;

import com.pingpongx.smb.fee.domain.runtime.BillingContext;

public class CalculateParamCompleted extends AbstractStage{


    public CalculateParamCompleted(BillingContext context) {
        super(context);
    }

    @Override
    public int getStagePriority() {
        return 3;
    }
}
