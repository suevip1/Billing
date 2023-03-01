package com.pingpongx.smb.metadata.adapter;

import com.pingpongx.smb.fee.dal.dataobject.ConfiguredVariableDef;
import com.pingpongx.smb.metadata.dto.VariableDefDto;

public class VariableDefAdapter implements Adapter{
    public static VariableDefDto do2Dto(ConfiguredVariableDef dataObject){
        VariableDefDto dto = new VariableDefDto();
        dto.setCode(dataObject.getCode());
        dto.setNameSpace(dataObject.getNameSpace());
        dto.setNum(dataObject.isNum());
        dto.setPath(dataObject.getPath());
        dto.setType(dataObject.getType());
        dto.setSourceType(dataObject.getSourceType());
        return dto;
    }
}
