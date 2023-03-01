package com.pingpongx.smb.fee.dependency.inner.service;

import com.pingpongx.smb.metadata.cmd.SaveOrUpdate;
import com.pingpongx.smb.metadata.dto.VariableDefDto;
import com.pingpongx.smb.metadata.qry.GetByCode;
import com.pingpongx.smb.metadata.qry.ListAll;

import java.util.List;

public interface VariableDefService {
    boolean saveOrUpdate( VariableDefDto dto);

    List<VariableDefDto> getByCode( GetByCode request);

    List<VariableDefDto> getByCode(ListAll request);
}
