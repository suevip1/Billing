package com.pingpongx.smb.fee.domain.module.coupon;

import com.pingpongx.smb.fee.api.dtos.cmd.CouponInfo;
import com.pingpongx.smb.fee.api.dtos.resp.CostItemResult;
import com.pingpongx.smb.fee.api.dtos.resp.coupon.CouponAction;
import com.pingpongx.smb.fee.domain.runtime.BillingContext;
import io.vavr.Tuple2;

public interface AfterCalculateHandler extends CouponHandler {
    void couponResultFix(CouponInfo couponInfo, BillingContext context);
}
