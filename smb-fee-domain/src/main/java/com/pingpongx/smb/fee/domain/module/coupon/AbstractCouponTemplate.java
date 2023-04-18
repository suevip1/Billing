package com.pingpongx.smb.fee.domain.module.coupon;

import com.pingpongx.smb.asset.api.coupon.enums.CouponTypeEnum;

public class AbstractCouponTemplate {
    /***
     * 优惠券类型
     */
    String couponType;
//    CouponTypeEnum couponTypeEnum;
    /**
     * Billing , BeforeBilling , AfterBilling
     */
    String stage;

    /**
     * 优惠券模板id
     */
    String couponId;


}
