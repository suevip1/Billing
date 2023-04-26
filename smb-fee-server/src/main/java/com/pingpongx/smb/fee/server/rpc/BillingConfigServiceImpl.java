package com.pingpongx.smb.fee.server.rpc;

import com.pingpongx.flowmore.cloud.base.server.annotation.Internal;
import com.pingpongx.flowmore.cloud.base.server.constants.RoleRegister;
import com.pingpongx.smb.fee.api.dtos.resp.Bill;
import com.pingpongx.smb.fee.api.feign.BillingConfigServiceFeign;
import com.pingpongx.smb.fee.domain.rules.Engines;
import com.pingpongx.smb.metadata.dto.VariableDefDto;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.security.RolesAllowed;


@Api(tags = "计费中心-配置")
@RestController
@RequestMapping(value = BillingConfigServiceFeign.BASE_PATH)
@Slf4j
@RequiredArgsConstructor
public class BillingConfigServiceImpl implements BillingConfigServiceFeign {

    private final Engines engines;


    @RolesAllowed(RoleRegister.ROLE_COMMON_SERVICE)
    @Internal
    @ApiImplicitParams({
            @ApiImplicitParam(name = "Authorization", value = "service smb-fee@test", required = false, paramType = "header"),
            @ApiImplicitParam(name = "appId", value = "test@smb-fee", required = false, paramType = "header"),
    })
    public void refresh(String costNode) {
        engines.rebuildEngine(costNode);
    }

    @Override
    public Bill saveOrUpdateVariable(VariableDefDto request) {
        return null;
    }


}
