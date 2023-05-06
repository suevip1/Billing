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
    String collectionItemCode;
    /***
     * 原币种  ，目标币种
     * Source , Target
     */
    String currencyType;
    Integer priority;
    String inOrOut;
    String  itemType;
    Long startTime;
    Long endTime;

    //定位问题更直观，不做时区处理，业务禁用
    LocalDateTime startTimeDisplay;
    LocalDateTime endTimeDisplay;

    String displayName;

    String contractCode;
    String costNodeCode;
}
