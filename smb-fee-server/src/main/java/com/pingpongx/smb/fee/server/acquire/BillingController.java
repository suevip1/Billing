package com.pingpongx.smb.fee.server.acquire;

import com.pingpongx.flowmore.cloud.base.commom.constants.VoidResponse;
import com.pingpongx.flowmore.cloud.base.server.annotation.NoAuth;
import com.pingpongx.flowmore.cloud.base.server.constants.RoleRegister;
import com.pingpongx.smb.fee.api.dtos.cmd.BillingRequest;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.security.RolesAllowed;

import static com.pingpongx.smb.fee.common.constants.FeeConstants.BASE_PATH;


@Api(tags = "计费")
@RestController
@RequestMapping(value = BASE_PATH + "/billing")
@Slf4j
public class BillingController {

    @ApiOperation("计费收单")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "Authorization", value = "Bearer <access_token>", required = true, paramType = "header"),
    })
    @PostMapping("/v1")
    @NoAuth
    @RolesAllowed(RoleRegister.ROLE_COMMON_SERVICE)
    public VoidResponse billing(@RequestParam BillingRequest request) {

        return new VoidResponse();
    }

}
