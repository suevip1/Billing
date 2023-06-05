package com.pingpongx.smb.fee.api.dtos.expr;

import com.fasterxml.jackson.annotation.JsonTypeInfo;
import com.pingpongx.smb.export.module.persistance.Range;
import lombok.Data;

@Data
public class NodeWithContidionDto {
    Range condition;

    @JsonTypeInfo(use = JsonTypeInfo.Id.NAME, property = "type", visible = true)
    ExprDto expr;
}

