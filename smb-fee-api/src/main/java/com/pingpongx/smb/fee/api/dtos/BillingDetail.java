package com.pingpongx.smb.fee.api.dtos;

import com.pingpongx.smb.fee.api.dtos.resp.CostItemResult;
import com.pingpongx.smb.fee.api.dtos.resp.CouponResult;
import lombok.Data;

import java.util.List;
@Data
public class BillingDetail {
    List<CostItemResult> fees;
    /****
     * 优惠券核销额度 Key : template Id. Value : 核销额度
     */
    List<CouponResult> expenses;
}
