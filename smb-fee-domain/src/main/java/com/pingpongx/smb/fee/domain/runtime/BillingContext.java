package com.pingpongx.smb.fee.domain.runtime;

import com.pingpongx.smb.fee.api.dtos.cmd.BillingRequest;
import com.pingpongx.smb.fee.api.dtos.resp.CostItemResult;
import com.pingpongx.smb.fee.api.dtos.resp.CouponResult;
import com.pingpongx.smb.fee.domain.module.CostItem;
import lombok.Data;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ConcurrentHashMap;

@Data
public class BillingContext implements Serializable {
    BillingRequest request;

    Map<String , Object> params = new HashMap<>();

    List<CostItemResult> feeResult = new ArrayList<>();

    List<CostItem> matchedCostItem;
    /****
     * 优惠券核销额度 Key : template Id. Value : 核销额度
     */
    List<CouponResult> expense = new ArrayList<>();

    boolean isTrial;

    /**
     * key：costItemCode ， val：calculateResult
     */
    ConcurrentHashMap<String , BigDecimal> cache = new ConcurrentHashMap<>();

    CompletableFuture<BillingContext> future;
}
