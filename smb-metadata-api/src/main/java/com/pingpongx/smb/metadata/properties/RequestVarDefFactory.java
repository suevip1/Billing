package com.pingpongx.smb.metadata.properties;

import com.pingpongx.smb.metadata.VarDefFactory;
import com.pingpongx.smb.metadata.VarType;
import com.pingpongx.smb.metadata.VariableDef;
import com.pingpongx.smb.metadata.dto.VariableDefDto;

public class RequestVarDefFactory implements VarDefFactory {
    public VariableDef toDomainObj(VariableDefDto def){
        if (!def.getType().equals(VarType.Request.getCode())){
            throw new RuntimeException("type not supported.");
        }
        RequestVar varDef = new RequestVar();
        varDef.setCode(def.getCode());
        varDef.setIsNum(def.isNum());
        varDef.setNamespace(def.getNameSpace());
        varDef.setPath(def.getPath());
        varDef.setSourceType(def.getSourceType());
        return varDef;
    }
}
