package com.pingpongx.smb.fee.domain.entity;

import com.pingpongx.smb.fee.domain.enums.Direction;
import com.pingpongx.smb.fee.domain.enums.CalculateMode;

import java.time.LocalDateTime;
import java.util.List;

public class CostItem {
    String code;
    String matchRule;
    List<String> matchVarKeys;
    String calculateExpress;
    List<String> calculateVarKeys;
    String displayName;
    CalculateMode mode;
    String collectionCode;
    /***
     * 原币种  ，目标币种
     * Source , Target
     */
    String currencyType;
    Long priority;
    Direction inOrOut;
    LocalDateTime startTime;
    LocalDateTime endTime;
}
