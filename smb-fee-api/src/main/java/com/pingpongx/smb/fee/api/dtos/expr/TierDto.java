package com.pingpongx.smb.fee.api.dtos.expr;

import com.fasterxml.jackson.annotation.JsonTypeInfo;

import java.util.List;

public class TierDto {

    @JsonTypeInfo(use = JsonTypeInfo.Id.NAME, property = "type",visible = true)
    List<ExprDto> list;
}
