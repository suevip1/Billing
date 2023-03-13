package com.pingpongx.smb.fee.api.dtos.rule;

import com.alibaba.fastjson2.annotation.JSONType;
import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import com.pingpongx.smb.export.module.persistance.And;
import com.pingpongx.smb.export.module.persistance.RuleDto;
import com.pingpongx.smb.fee.api.dtos.expr.*;
import lombok.Data;

import java.io.Serializable;
import java.util.List;
import java.util.stream.Collectors;

@Data
@JSONType(typeName = "OrRuleDto")
public  class OrRuleDto extends MatchRuleDto {
    List<MatchRuleDto> list;
    String type = "Or";
    public RuleDto toEngineRule(){
        And and = new And();
        List<RuleDto> l = list.stream().map(one->one.toEngineRule()).collect(Collectors.toList());
        and.setAndRules(l);
        return and;
    }
}
