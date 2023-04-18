package com.pingpongx.smb.fee.domain.module.coupon;

import com.pingpongx.smb.asset.api.coupon.enums.CouponTypeEnum;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor
public class CouponHandlerFactory {
    private final List<CouponHandler> couponHandler;
    private volatile Map<String,CouponHandler> map;

    public CouponHandler getCouponHandler(String couponType){
        if (map == null){
            map = couponHandler.stream().collect(Collectors.toMap(h->h.supportedType(),h->h));
        }
        return map.get(couponType);
    }

    public CouponHandler getCouponHandler(CouponTypeEnum couponType){
        if (map == null){
            map = couponHandler.stream().collect(Collectors.toMap(h->h.supportedType(),h->h));
        }
        return map.get(couponType.name());
    }
}
