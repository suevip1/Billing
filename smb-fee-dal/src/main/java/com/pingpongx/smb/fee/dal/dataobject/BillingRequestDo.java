package com.pingpongx.smb.fee.dal.dataobject;

import com.pingpongx.business.dal.core.BaseDO;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import java.math.BigDecimal;

/**
 * @author wangcheng
 * @Description TODO
 * @createTime 2022年06月28日 11:54:00
 */
@Data
@EqualsAndHashCode(callSuper = true)
@Accessors(chain = true)
public class BillingRequestDo extends FeeBaseDo {

    private static final long serialVersionUID = 8061568762858026972L;

    private String scope;

    private String bu;

    private String orderType;

    private Integer configType;

    private String originCurrency;

    private String targetCurrency;

    private BigDecimal feeRate;

    private BigDecimal cutFeeRate;

    private String fixFee;

    private String minPayout;

    private String lessThanMinPayoutFee;

    private String maxFee;

    private String operator;

    private String remark;

    private String extraInfo;


}
