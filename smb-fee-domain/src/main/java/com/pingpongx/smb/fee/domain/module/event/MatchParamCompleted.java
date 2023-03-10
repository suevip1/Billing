package com.pingpongx.smb.fee.domain.module.event;

import com.pingpongx.smb.fee.domain.runtime.BillingContext;

public class MatchParamCompleted extends AbstractStage{

    public MatchParamCompleted(BillingContext context) {
        super(context);
    }

    @Override
    public int getStagePriority() {
        return 1;
    }
}
