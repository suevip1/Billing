package com.pingpongx.smb.fee.domain.module.event;

import com.pingpongx.smb.fee.domain.runtime.BillingContext;

public class MatchCompleted extends AbstractStage{

    public MatchCompleted(BillingContext context) {
        super(context);
    }

    @Override
    public boolean isImportant() {
        return false;
    }

    @Override
    public int getStagePriority() {
        return 2;
    }
}
