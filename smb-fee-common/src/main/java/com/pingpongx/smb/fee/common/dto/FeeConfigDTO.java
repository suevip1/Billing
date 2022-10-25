package com.pingpongx.smb.fee.common.dto;

import com.pingpongx.business.common.dto.Money;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;
import java.math.BigDecimal;

/**
 * @author wangcheng
 * @Description TODO
 * @createTime 2022年06月28日 15:10:00
 */
@Data
public class FeeConfigDTO implements Serializable {

    private static final long serialVersionUID = 7885949012326313655L;
    private Integer id;

    @ApiModelProperty(example = "部门:B2B,FLOWMORE,SMB,MPT")
    private String bu;

    @ApiModelProperty(example = "全局ALL,地区HZ,US等,个人clientId")
    private String scope;

    @ApiModelProperty(example = "0全局 1个人")
    private Integer configType;

    @ApiModelProperty(example = "原始币种")
    private String originCurrency;

    @ApiModelProperty(example = "目标币种")
    private String targetCurrency;

    @ApiModelProperty(example = "订单业务类型")
    private String orderType;

    @ApiModelProperty(example = "费率")
    private BigDecimal feeRate;

    @ApiModelProperty(example = "{\"amount\":0,\"currency\":\"DEFAULT\"}优惠费率")
    private BigDecimal cutFeeRate;

    @ApiModelProperty(example = "{\"amount\":0,\"currency\":\"DEFAULT\"}固定费率")
    private Money fixFee;

    @ApiModelProperty(example = "{\"amount\":0,\"currency\":\"DEFAULT\"}最低提现金额")
    private Money minPayout;

    @ApiModelProperty(example = "{\"amount\":0,\"currency\":\"DEFAULT\"}低于最低提现金额需要额外收取的手续费")
    private Money lessThanMinPayoutFee;

    @ApiModelProperty(example = "{\"amount\":0,\"currency\":\"DEFAULT\"}最高手续费")
    private Money maxFee;

    @ApiModelProperty(example = "操作者")
    private String operator;

    @ApiModelProperty(example = "备注")
    private String remark;

    @ApiModelProperty(example = "额外信息(预留字段)")
    private String extraInfo;

}
