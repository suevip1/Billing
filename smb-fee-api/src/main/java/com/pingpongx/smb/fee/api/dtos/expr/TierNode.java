package com.pingpongx.smb.fee.api.dtos.expr;

import com.fasterxml.jackson.annotation.JsonTypeInfo;
import com.pingpongx.smb.export.module.persistance.Or;
import lombok.Data;

import java.util.List;
@Data
public class TierNode {
    Or condition;

    @JsonTypeInfo(use = JsonTypeInfo.Id.NAME, property = "type",visible = true)
    ExprDto expr;
}
