package com.pingpongx.smb.fee.domain.module.coupon;

import com.pingpongx.smb.fee.api.dtos.cmd.common.CouponInfo;
import com.pingpongx.smb.fee.domain.runtime.BillingContext;

public interface AfterCalculateHandler extends CouponHandler {
    void couponResultFix(CouponInfo couponInfo, BillingContext context);
}
