package com.pingpongx.smb.fee.domain.module.coupon;

import com.pingpongx.smb.fee.api.dtos.cmd.CouponInfo;
import com.pingpongx.smb.fee.api.dtos.resp.coupon.CouponAction;
import com.pingpongx.smb.fee.api.dtos.resp.coupon.CreditDecrease;
import com.pingpongx.smb.fee.domain.runtime.BillingContext;
import io.vavr.Tuple2;
import io.vavr.Tuple3;

import java.math.BigDecimal;

public interface CouponHandler {
    String supportedType();

}
