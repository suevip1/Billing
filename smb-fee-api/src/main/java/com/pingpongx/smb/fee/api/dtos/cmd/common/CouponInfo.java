package com.pingpongx.smb.fee.api.dtos.cmd.common;

import com.pingpongx.smb.metadata.Identified;
import lombok.Data;

import java.io.Serializable;
import java.math.BigDecimal;

@Data
public class CouponInfo implements Serializable, Identified {

    private static final long serialVersionUID = 8065156876028580972L;

    String userCouponId;

    String currency; //source / target

    BigDecimal validValue;

    /**
     * 优惠券类型  CouponTypeEnum.xxxx.name()
     */
    String couponType;
    /**
     * 冲销模板id
     */
    String couponId;


    public String identify() {
        String split = "-";
        StringBuilder builder = new StringBuilder();
        builder.append(couponId).append(split).append(validValue);
        return builder.toString();
    }
}
