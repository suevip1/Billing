package com.pingpongx.smb.fee.domain.runtime;

import com.pingpongx.smb.fee.api.dtos.cmd.BillingRequest;
import com.pingpongx.smb.fee.domain.entity.CostItem;
import lombok.Data;
import org.joda.money.Money;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ConcurrentHashMap;

@Data
public class BillingContext {
    BillingRequest request;

    Map<String , Object> params = new HashMap<>();;

    Map<String,Money> feeResult = new HashMap<>();

    List<CostItem> matchedCostItem;
    /****
     * 优惠券核销额度 Key : template Id. Value : 核销额度
     */
    Map<String , BigDecimal> expense = new HashMap<>();;

    boolean isTrial;

    /**
     * key：costItemCode ， val：calculateResult
     */
    ConcurrentHashMap<String , BigDecimal> cache = new ConcurrentHashMap<>();

    CompletableFuture<BillingContext> future;
}
