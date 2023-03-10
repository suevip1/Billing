package com.pingpongx.smb.fee.dal.dataobject;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * @author wangcheng
 * @Description TODO
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
    String request;
    String params;
    String matchedCostItem;
    String bill;
    /**
     * key：costItemCode ， val：calculateResult
     */
    String cache;
}
