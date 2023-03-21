package com.pingpongx.smb.fee.api.dtos.rule;

import com.alibaba.fastjson2.annotation.JSONType;
import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import com.pingpongx.smb.export.module.persistance.RuleDto;
import com.pingpongx.smb.fee.api.dtos.expr.*;
import lombok.Data;

import java.io.Serializable;

@Data
@JsonTypeInfo(use = JsonTypeInfo.Id.NAME, property = "type", visible = true)
@JsonSubTypes({@JsonSubTypes.Type(value = LeafRuleDto.class, name = "Leaf"), @JsonSubTypes.Type(value = AndRuleDto.class, name = "And"), @JsonSubTypes.Type(value = OrRuleDto.class, name = "Or")})
@JSONType(seeAlso = {LeafRuleDto.class, AndRuleDto.class, OrRuleDto.class})
public abstract class MatchRuleDto implements Serializable {
    public abstract String getType();
    public abstract RuleDto toEngineRule();
}
