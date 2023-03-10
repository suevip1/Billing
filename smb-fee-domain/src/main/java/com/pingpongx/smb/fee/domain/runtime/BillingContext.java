package com.pingpongx.smb.fee.domain.runtime;

import com.pingpongx.smb.fee.api.dtos.cmd.BillingRequest;
import com.pingpongx.smb.fee.api.dtos.resp.CostItemResult;
import com.pingpongx.smb.fee.api.dtos.resp.CouponResult;
import com.pingpongx.smb.fee.domain.module.CostItem;
import com.pingpongx.smb.fee.domain.module.event.BillingRequestReceived;
import com.pingpongx.smb.fee.domain.module.event.BillingStage;
import com.pingpongx.smb.fee.domain.module.event.CalculateCompleted;
import com.pingpongx.smb.fee.domain.module.event.MatchCompleted;
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

    List<CostItem> matchedCostItem;

    List<CostItemResult> feeResult = new ArrayList<>();

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

    public BillingStage resume(CompletableFuture<BillingContext> future){
        this.setFuture(future);
        if (matchedCostItem == null){
            return new BillingRequestReceived(this);
        }
        if (feeResult==null && expense == null){
            return new MatchCompleted(this);
        }
        return new CalculateCompleted(this);
    }
}
