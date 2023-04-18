package com.pingpongx.smb.fee.api.dtos.resp;

import com.pingpongx.smb.asset.api.coupon.dto.UserCouponConsumeDto;
import com.pingpongx.smb.fee.api.dtos.cmd.CouponInfo;
import com.pingpongx.smb.fee.api.dtos.resp.coupon.CouponAction;
import lombok.Data;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Data
public class CouponResult implements Serializable {

    private static final long serialVersionUID = 8061568762858026972L;

    String couponId;
    String userCouponId;
    CouponAction couponAction;
    public static CouponResult of(CouponInfo couponInfo,CouponAction couponAction){
        CouponResult ret = new CouponResult();
        ret.setCouponId(couponInfo.getCouponId());
        ret.setUserCouponId(couponInfo.getUserCouponId());
        ret.setCouponAction(couponAction);
        return ret;
    }
}
