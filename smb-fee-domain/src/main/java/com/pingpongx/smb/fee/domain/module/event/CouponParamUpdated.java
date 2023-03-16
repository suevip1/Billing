package com.pingpongx.smb.fee.domain.module.event;

import com.pingpongx.smb.fee.domain.runtime.BillingContext;

public class CouponParamUpdated extends AbstractStage {

    public CouponParamUpdated(BillingContext context) {
        super(context);
    }

    @Override
    public int getStagePriority() {
        return 4;
    }
}
