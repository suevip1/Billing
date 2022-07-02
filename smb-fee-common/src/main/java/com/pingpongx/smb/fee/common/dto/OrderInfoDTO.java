package com.pingpongx.smb.fee.common.dto;

import com.pingpongx.business.common.constant.OrderType;
import com.pingpongx.business.common.dto.Money;
import lombok.Data;
import java.io.Serializable;
import java.util.Map;


/**
 * @author wangcheng
 * @Description TODO
 * @createTime 2022年07月01日 13:54:00
 */
@Data
public class OrderInfoDTO implements Serializable {
    private static final long serialVersionUID = 4104780889666200339L;

    /**
     * 订单号
     */
    private String orderId;
    /**
     * 部门:B2B,FLOWMORE,SMB,MPT
     */
    private String bu;
    /**
     * 原始金额信息
     */
    private Money originMoney;
    /**
     * 目标金额信息
     */
    private Money targetMoney;
    /**
     * 用户唯一id
     */
    private String clientId;
    /**
     * 订单类型
     */
    private String orderType;
    /**
     * 地区
     */
    private String location;
    /**
     * 额外信息
     */
    private Map<String, Object> extraInfo;

}
