package com.pingpongx.smb.metadata.service;

import com.pingpongx.smb.fee.dal.dataobject.ConfiguredVariableDef;
import com.pingpongx.smb.fee.dal.repository.VariableDefRepository;
import com.pingpongx.smb.fee.dependency.inner.service.VariableDefService;
import com.pingpongx.smb.metadata.adapter.VariableDefAdapter;
import com.pingpongx.smb.metadata.cmd.SaveOrUpdate;
import com.pingpongx.smb.metadata.dto.VariableDefDto;
import com.pingpongx.smb.metadata.qry.GetByCode;
import com.pingpongx.smb.metadata.qry.ListAll;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class VariableDefServiceImpl implements VariableDefService {
    private final VariableDefRepository repository;
    @Override
    public boolean saveOrUpdate(VariableDefDto dto) {
        ConfiguredVariableDef def = new ConfiguredVariableDef();
        def.setCode(dto.getCode());
        def.setPath(dto.getPath());
        def.setNameSpace(dto.getNameSpace());
        def.setSourceType(dto.getSourceType());
        def.setNum(dto.isNum());
        def.setType(dto.getType());
        return repository.saveOrUpdate(def);
    }

    @Override
    public List<VariableDefDto> listAll(List<String> request) {
        return repository.getByCode(request).stream()
                .map(VariableDefAdapter::do2Dto)
                .collect(Collectors.toList());
    }

    @Override
    public List<VariableDefDto> listAll(String nameSpace) {
        return repository.listAll(nameSpace).stream()
                .map(VariableDefAdapter::do2Dto)
                .collect(Collectors.toList());
    }
}
