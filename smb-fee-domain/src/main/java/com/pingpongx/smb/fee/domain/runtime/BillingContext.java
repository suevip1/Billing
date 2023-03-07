package com.pingpongx.smb.fee.domain.runtime;

import lombok.Data;
import org.joda.money.Money;

import java.math.BigDecimal;
import java.util.Map;
@Data
public class BillingContext {
    String order;
    Map<String , Object> params;
    Money feeResult;
    /****
     * 优惠券核销额度 Key : template Id. Value : 核销额度
     */
    Map<String , BigDecimal> expense;
}
