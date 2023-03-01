package com.pingpongx.smb.metadata.service;

import com.pingpongx.smb.fee.dal.dataobject.ConfiguredVariableDef;
import com.pingpongx.smb.fee.dal.repository.VariableDefRepository;
import com.pingpongx.smb.metadata.DefCommonFactory;
import com.pingpongx.smb.metadata.VariableDef;
import com.pingpongx.smb.metadata.dto.VariableDefDto;
import lombok.RequiredArgsConstructor;
import org.apache.ibatis.annotations.ConstructorArgs;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class VarDefService {
    private final VariableDefRepository repository;
    boolean saveOrUpdate(VariableDefDto dto){
        ConfiguredVariableDef def = new ConfiguredVariableDef();
        def.setCode(dto.getCode());
        def.setPath(dto.getPath());
        def.setNameSpace(dto.getNameSpace());
        def.setSourceType(dto.getSourceType());
        def.setNum(dto.isNum());
        def.setType(dto.getType());
        return repository.saveOrUpdate(def);
    }
}
