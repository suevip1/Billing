package com.pingpongx.smb.metadata;

import com.pingpongx.smb.metadata.dto.VariableDefDto;

public interface VarDefFactory {
    VariableDef toDomainObj(VariableDefDto def);
}
