package com.pingpongx.smb.fee.dal.dataobject;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import java.math.BigDecimal;
import java.util.Map;

/**
 * @author wangcheng
 * @Description TODO
 * @createTime 2022年06月28日 11:54:00
 */
@Data
@EqualsAndHashCode(callSuper = true)
@Accessors(chain = true)
@TableName("billing_request")
public class BillingRequestDo extends FeeBaseDo {

    private static final long serialVersionUID = 8061568762858026972L;

    /***
     * 计费时间
     */
    Long billingTime;
    /***
     * 计费节点
     */
    String costNodeCode;
    /***
     * Did buClientId 具体数据
     */
    String subject;
    /***
     * Did / accid / Nid / buClientId
     */
    String subjectType;
    /***
     * 汇率列表，目标货种：原币种  目标币种：美元  原币种：美元
     */
    String fxRate;
    /**
     * 用于幂等
     */
    String fxRateId;
    String orderInfo;

    String bizOrderId;

    String bizOrderType;
    /**
     * 来源系统 B2B FM Dispatch Common
     */
    String bizLine;
    /**
     * 来源应用
     */
    String sourceApp;
    /**
     * 优惠券
     */
    String couponList;

    String repeatKey;
}
