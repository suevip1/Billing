package com.pingpongx.smb.fee.domain.module.event;

import com.pingpongx.smb.fee.domain.runtime.BillingContext;

public class CouponResultCompleted extends AbstractStage {

    public CouponResultCompleted(BillingContext context) {
        super(context);
    }

    @Override
    public int getStagePriority() {
        return 7;
    }
}
