package com.pingpongx.smb.fee.api.dtos.expr;

import com.alibaba.fastjson2.annotation.JSONType;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import lombok.Data;

import java.util.List;
@Data
@JSONType(typeName = "TierDto")
public class TierDto extends ExprDto{

    @JsonTypeInfo(use = JsonTypeInfo.Id.NAME, property = "type",visible = true)
    List<NodeWithContidionDto> list;
    String type = "Tier";
}
