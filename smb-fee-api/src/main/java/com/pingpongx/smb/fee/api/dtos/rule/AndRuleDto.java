package com.pingpongx.smb.fee.api.dtos.rule;

import com.alibaba.fastjson2.annotation.JSONType;
import com.pingpongx.smb.export.module.operation.RuleAnd;
import com.pingpongx.smb.export.module.persistance.And;
import com.pingpongx.smb.export.module.persistance.LeafRuleConf;
import com.pingpongx.smb.export.module.persistance.RuleDto;
import lombok.Data;

import java.util.List;
import java.util.stream.Collectors;

@Data
@JSONType(typeName = "AndRuleDto")
public  class AndRuleDto extends MatchRuleDto {
    String type = "And";
    List<MatchRuleDto> list;

    @Override
    public String getType() {
        return type;
    }

    public RuleDto toEngineRule(){
        And and = new And();
        List<RuleDto> l = list.stream().map(one->one.toEngineRule()).collect(Collectors.toList());
        and.setAndRules(l);
        return and;
    }
}
