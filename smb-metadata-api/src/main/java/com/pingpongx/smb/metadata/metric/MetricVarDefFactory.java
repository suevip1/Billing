package com.pingpongx.smb.metadata.metric;

import com.pingpongx.smb.metadata.VarDefFactory;
import com.pingpongx.smb.metadata.VariableDef;
import com.pingpongx.smb.metadata.dto.VariableDefDto;

public class MetricVarDefFactory implements VarDefFactory {
    public VariableDef toDomainObj(VariableDefDto def){
        throw new RuntimeException("type not supported.");
//        if (!def.getType().equals(VarType.Metric.getCode())){
//            throw new RuntimeException("type not supported.");
//        }
//        MetricVar varDef = new MetricVar();
//        return varDef;
    }
}
