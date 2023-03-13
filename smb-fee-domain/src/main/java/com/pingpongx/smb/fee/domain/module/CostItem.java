package com.pingpongx.smb.fee.domain.module;

import com.pingpongx.smb.export.module.Rule;
import com.pingpongx.smb.fee.domain.enums.CalculateMode;
import com.pingpongx.smb.fee.domain.enums.Direction;
import com.pingpongx.smb.fee.domain.module.express.Expr;
import io.swagger.models.auth.In;
import lombok.Data;

import java.io.Serializable;
import java.time.LocalDateTime;
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
    /***
     * 原币种  ，目标币种
     * Source , Target
     */
    String currencyType;
    Integer priority;
    Direction inOrOut;
    LocalDateTime startTime;
    LocalDateTime endTime;

    String displayName;
    String contractCode;
    String costNodeCode;
}
