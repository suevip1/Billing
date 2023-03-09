package com.pingpongx.smb.fee.dal.dataobject;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @author wangcheng
 * @Description TODO
 * @createTime 2022年06月28日 11:54:00
 */
@Data
@EqualsAndHashCode(callSuper = true)
@Accessors(chain = true)
public class BillingProcessDo extends FeeBaseDo {

    private static final long serialVersionUID = 8061568762858026972L;
    //冗余 定位问题方便搜索
    String bizOrderId;
    String bizOrderType;
    String requestRepeatKey;

    //Context实际字段
    String request;
    String params;
    String feeResult;
    String matchedCostItem;
    /****
     * 优惠券核销额度 Key : template Id. Value : 核销额度
     */
    String expense;
    /**
     * key：costItemCode ， val：calculateResult
     */
    String cache;
}
