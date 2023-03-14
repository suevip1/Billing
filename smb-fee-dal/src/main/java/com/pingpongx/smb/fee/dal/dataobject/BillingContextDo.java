package com.pingpongx.smb.fee.dal.dataobject;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import java.math.BigDecimal;
import java.util.HashMap;

/**
 * @author xuyq
 * @createTime 2022年06月28日 11:54:00
 */
@Data
@EqualsAndHashCode(callSuper = true)
@Accessors(chain = true)
public class BillingContextDo extends FeeBaseDo {

    private static final long serialVersionUID = 8061568762858026972L;
    //冗余 定位问题方便搜索
    String bizOrderId;
    String bizOrderType;
    String requestRepeatKey;

    //Context实际字段
    /**
     * 请求体
     */
    String request;
    /**
     * 参数缓存
     */
    String params;
    /**
     * 匹配结果
     */
    String matchedCostItem;
    /**
     * 计算结果
     */
    String bill;
    /**
     * key：costItemCode ， val：calculateResult
     */
    String calculateResult;
    String failedReasons;
}
