package com.pingpongx.smb.fee.domain.runtime;

import com.pingpongx.smb.fee.api.dtos.resp.Bill;
import com.pingpongx.smb.fee.domain.module.CostItem;
import com.pingpongx.smb.fee.domain.module.Request;
import com.pingpongx.smb.fee.domain.module.event.BillingRequestReceived;
import com.pingpongx.smb.fee.domain.module.event.BillingStage;
import com.pingpongx.smb.fee.domain.module.event.CalculateCompleted;
import com.pingpongx.smb.fee.domain.module.event.MatchCompleted;
import lombok.Data;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ConcurrentHashMap;

@Data
public class BillingContext implements Serializable {
    Long id;
    Request request;
    Map<String, String> params = new HashMap<>();
    List<CostItem> matchedCostItem;
    Bill bill;

    boolean isTrial;

    /**
     * key：costItemCode ， val：calculateResult
     * key: costCollectionCode , val calculateResult
     */
    ConcurrentHashMap<String, BigDecimal> cache = new ConcurrentHashMap<>();

    CompletableFuture<BillingContext> future;

    public BillingStage resume(CompletableFuture<BillingContext> future) {
        this.setFuture(future);
        if (matchedCostItem == null) {
            return new BillingRequestReceived(this);
        }
        if (bill == null) {
            return new MatchCompleted(this);
        }
        return new CalculateCompleted(this);
    }

    public String getNameSpace(){
        return getRequest().getNameSpace();
    }
}
