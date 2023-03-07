package com.pingpongx.smb.fee.domain.entity;

import com.pingpongx.smb.fee.domain.enums.Direction;
import com.pingpongx.smb.fee.domain.enums.ItemMode;

import java.time.LocalDateTime;
import java.util.List;

public class CostItem {
    String code;
    String matchRule;
    List<String> matchVarKeys;
    String calculateExpress;
    List<String> calculateVarKeys;
    String displayName;
    ItemMode mode;
    String collectionCode;
    String currency;
    Long priority;
    Direction inOrOut;
    LocalDateTime startTime;
    LocalDateTime endTime;
}
