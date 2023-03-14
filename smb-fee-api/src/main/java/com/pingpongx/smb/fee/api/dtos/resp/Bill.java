package com.pingpongx.smb.fee.api.dtos.resp;

import com.pingpongx.smb.fee.api.dtos.resp.coupon.CouponAction;
import lombok.Data;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Data
public class Bill implements Serializable {

    private static final long serialVersionUID = 8063154680261469672L;


    List<CostItemResult> feeResult = new ArrayList<>();

    /****
     * 优惠券核销额度 Key : template Id. Value : 核销额度
     */
    List<CouponResult> expense = new ArrayList<>();

    /***
     * 失败原因
     */
    Map<String , String> failedReasons = new HashMap<>();

}
