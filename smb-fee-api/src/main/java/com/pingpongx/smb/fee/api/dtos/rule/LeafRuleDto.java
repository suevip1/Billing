package com.pingpongx.smb.fee.api.dtos.rule;

import com.alibaba.fastjson2.annotation.JSONType;
import com.pingpongx.smb.export.module.persistance.LeafRuleConf;
import lombok.Data;

@Data
@JSONType(typeName = "LeafRuleDto")
public  class LeafRuleDto extends MatchRuleDto {
    String type = "Leaf";

    String varCode;
    String operation;
    String expected;
    boolean not;

    public LeafRuleConf toEngineRule(){
        LeafRuleConf conf = new LeafRuleConf();
        conf.setType("map");
        conf.setNot(not);
        conf.setAttr(varCode);
        conf.setExpected(expected);
        conf.setOperation(operation);
        return conf;
    }
}
