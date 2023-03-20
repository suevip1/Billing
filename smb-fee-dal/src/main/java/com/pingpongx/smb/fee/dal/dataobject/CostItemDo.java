package com.pingpongx.smb.fee.dal.dataobject;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import java.time.LocalDateTime;

@Data
@EqualsAndHashCode(callSuper = true)
@Accessors(chain = true)
@TableName("cost_item")
public class CostItemDo extends FeeBaseDo {

    private static final long serialVersionUID = 8036156681328806972L;
    String code;
    String matchRule;
    String matchVarKeys;
    String calculateExpress;
    String calculateVarKeys;
    String mode;
    String collectionCode;
    /***
     * 原币种  ，目标币种
     * Source , Target
     */
    String currencyType;
    Integer priority;
    String inOrOut;
    Long startTime;
    Long endTime;

    String displayName;

    String contractCode;
    String costNodeCode;
}
