package com.pingpongx.smb.fee.domain.module.event;

import com.pingpongx.smb.fee.domain.runtime.BillingContext;

public class CalculateFailed extends AbstractStage {

    public CalculateFailed(BillingContext context) {
        super(context);
    }

    @Override
    public boolean isImportant() {
        return true;
    }

    @Override
    public int getStagePriority() {
        return 10000;
    }
}
