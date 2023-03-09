package com.pingpongx.smb.fee.api.dtos.cmd;

import com.pingpongx.smb.metadata.Identified;
import lombok.Data;

import java.io.Serializable;
import java.math.BigDecimal;
@Data
public class CouponInfo implements Serializable, Identified {

    private static final long serialVersionUID = 8065156876028580972L;

    String couponId;
    BigDecimal usdCredit;
    /**
     * 优惠券类型
     */
    String couponType;
    /**
     * 冲销模板id
     */
    String templateId;
    /**
     * Billing , BeforeBilling , AfterBilling
     */
    String stage;
    /**
     *  credit 按额度核销 , count 按次数核销
     */
    String writeOffType;

    public String identify(){
        String split = "-";
        StringBuilder builder = new StringBuilder();
        builder.append(couponId).append(split).append(usdCredit);
        return builder.toString();
    }
}
