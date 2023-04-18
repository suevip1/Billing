package com.pingpongx.smb.fee.domain.module.coupon;

import com.pingpongx.smb.fee.api.dtos.cmd.CouponInfo;
import com.pingpongx.smb.fee.api.dtos.resp.coupon.CouponAction;
import com.pingpongx.smb.fee.domain.runtime.BillingContext;
import io.vavr.Tuple3;

import java.math.BigDecimal;

public interface BeforeCalculateHandler extends CouponHandler{
    Tuple3<String,BigDecimal, CouponAction> couponParamFix(CouponInfo couponInfo, BillingContext context);
}
