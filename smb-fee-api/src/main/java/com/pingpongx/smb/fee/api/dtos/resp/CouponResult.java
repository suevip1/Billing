package com.pingpongx.smb.fee.api.dtos.resp;

import com.pingpongx.smb.fee.api.dtos.cmd.CouponInfo;
import com.pingpongx.smb.fee.api.dtos.resp.coupon.CouponAction;
import com.pingpongx.smb.fee.api.dtos.resp.coupon.CreditDecrease;
import lombok.Data;

import java.io.Serializable;
import java.math.BigDecimal;

@Data
public class CouponResult implements Serializable {

    private static final long serialVersionUID = 8061568762858026972L;

    String couponCode;
    String userCouponCode;
    String currency;
    BigDecimal amount;

    //    CouponAction couponAction;
    public static CouponResult of(CouponInfo couponInfo, CouponAction couponAction) {
        CouponResult ret = new CouponResult();
        ret.setCouponCode(couponInfo.getCouponId());
        ret.setUserCouponCode(couponInfo.getUserCouponId());
        ret.setCurrency(((CreditDecrease) couponAction).getCurrency());
        ret.setAmount(((CreditDecrease) couponAction).getAmount());
        return ret;
    }
}
