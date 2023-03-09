package com.pingpongx.smb.fee.api.dtos.resp.coupon;

import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;

@JsonTypeInfo(use = JsonTypeInfo.Id.NAME, property = "type",visible = true)
@JsonSubTypes({
        @JsonSubTypes.Type(value = CountDecrease.class, name = "CountDecrease"),
        @JsonSubTypes.Type(value = CreditDecrease.class, name = "CreditDecrease"),
        @JsonSubTypes.Type(value = Gifts.class, name = "Gifts"),
        @JsonSubTypes.Type(value = ScoreIncrease.class, name = "ScoreIncrease")
})
public class CouponActionBase implements CouponAction{
    String couponType;
}
