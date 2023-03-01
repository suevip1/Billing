package com.pingpongx.smb.metadata.controller;

import com.pingpongx.flowmore.cloud.base.commom.constants.RoleRegister;
import com.pingpongx.flowmore.cloud.base.server.annotation.NoAuth;
import com.pingpongx.smb.fee.dal.dataobject.ConfiguredVariableDef;
import com.pingpongx.smb.fee.dal.repository.VariableDefRepository;
import com.pingpongx.smb.fee.dependency.inner.service.VariableDefService;
import com.pingpongx.smb.metadata.cmd.SaveOrUpdate;
import com.pingpongx.smb.metadata.dto.VariableDefDto;
import com.pingpongx.smb.metadata.qry.GetByCode;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.annotation.security.RolesAllowed;
import java.util.List;

@Controller
@RequiredArgsConstructor
@RequestMapping()
public class VarDefController {
    private final VariableDefService service;
    @ApiOperation("保存变量定义")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "Authorization", value = "Bearer <access_token>", required = true, paramType = "header"),
    })
    @PostMapping("/v1/saveOrUpdate")
    @NoAuth
    @RolesAllowed(RoleRegister.ROLE_COMMON_SERVICE)
    boolean saveOrUpdate(SaveOrUpdate dto){
        return service.saveOrUpdate(dto.getDefinition());
    }

    @ApiOperation("根据code 获取指定变量定义")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "Authorization", value = "Bearer <access_token>", required = true, paramType = "header"),
    })
    @PostMapping("/v1")
    @NoAuth
    @RolesAllowed(RoleRegister.ROLE_COMMON_SERVICE)
    List<VariableDefDto> getByCode(GetByCode req){
        return service.getByCode(req);
    }
}
