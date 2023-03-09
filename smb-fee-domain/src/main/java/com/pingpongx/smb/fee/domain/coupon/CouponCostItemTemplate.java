package com.pingpongx.smb.fee.domain.coupon;

import com.pingpongx.smb.fee.domain.module.CostItem;
import com.pingpongx.smb.fee.domain.module.CostNode;
import com.pingpongx.smb.fee.domain.enums.Direction;
import com.pingpongx.smb.fee.domain.enums.CalculateMode;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.List;
@Data
public class CouponCostItemTemplate {
    String code;
    String displayName;
    /***
     * 优惠券对指定的费用项生效，如果不为空则匹配条件等均与目标费用项一致。
     */
    String costItemCode;
//code 为空时生效
    String matchRule;
    List<String> matchVarKeys;
    CalculateMode mode;
    //    暂不支持优惠券优惠公式使用 复杂组合
    //    String collectionCode;
    /***
     * 原币种  ，目标币种
     * Source , Target
     */
    String currencyType;
    Long priority;
    Direction inOrOut;


//code 无论如何都生效
    String calculateExpress;
    List<String> calculateVarKeys;
    LocalDateTime startTime;
    LocalDateTime endTime;

    /**
     * 根据模板生成费用项
     * @param node
     * @return
     */
    CostItem generateCostItem(CostNode node){
        //TODO
        return new CostItem();
    }
}
