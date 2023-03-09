package com.pingpongx.smb.fee.api.dtos.resp;

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

    String templateCode;
    Long couponId;

    CouponAction couponAction;
}
