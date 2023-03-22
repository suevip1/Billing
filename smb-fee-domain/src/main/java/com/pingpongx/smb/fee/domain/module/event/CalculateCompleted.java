package com.pingpongx.smb.fee.domain.module.event;

import com.pingpongx.smb.fee.domain.runtime.BillingContext;

public class CalculateCompleted extends AbstractStage {

    public CalculateCompleted(BillingContext context) {
        super(context);
    }

    @Override
    public boolean isImportant() {
        return false;
    }

    @Override
    public int getStagePriority() {
        return 5;
    }
}
