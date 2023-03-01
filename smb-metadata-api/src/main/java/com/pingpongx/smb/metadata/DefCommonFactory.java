package com.pingpongx.smb.metadata;

import com.pingpongx.smb.metadata.dto.VariableDefDto;

public class DefCommonFactory implements VarDefFactory {
    public static VariableDef toDomain(VariableDefDto def){
        return VarType.typeOf(def.getType()).getDefFactory().toDomainObj(def);
    }

    @Override
    public VariableDef toDomainObj(VariableDefDto def) {
        return toDomain(def);
    }
}

