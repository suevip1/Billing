package com.pingpongx.smb.fee.domain.module;

import com.pingpongx.smb.export.module.Rule;
import com.pingpongx.smb.fee.api.enums.CalculateMode;
import com.pingpongx.smb.fee.api.enums.CurrencyType;
import com.pingpongx.smb.fee.api.enums.Direction;
import com.pingpongx.smb.fee.api.enums.ItemType;
import com.pingpongx.smb.fee.domain.module.express.Expr;
import lombok.Data;

import java.io.Serializable;
import java.util.List;

@Data
public class CostItem implements Serializable {
    private static final long serialVersionUID = -5131494290628937192L;

    String code;
    Rule matchRule;
    List<String> matchVarKeys;
    Expr calculateExpress;
    List<String> calculateVarKeys;
    CalculateMode mode;
    String collectionCode;
    String collectionItemCode;
    /***
     * 原币种  ，目标币种
     * Source , Target
     */
    CurrencyType currencyType;
    Integer priority;
    Direction inOrOut;
    ItemType itemType;
    Long startTime;
    Long endTime;

    String displayName;
    String contractCode;
    String costNodeCode;
}
