package com.pingpongx.smb.fee.domain.module.event;

import com.pingpongx.smb.fee.domain.runtime.BillingContext;

public interface BillingStage {
    String getStageCode();
    int getStagePriority();
    BillingContext getContext();
}
