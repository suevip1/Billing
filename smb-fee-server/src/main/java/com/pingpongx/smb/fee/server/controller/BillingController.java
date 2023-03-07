package com.pingpongx.smb.fee.server.controller;

import com.pingpongx.flowmore.cloud.base.commom.constants.VoidResponse;
import com.pingpongx.flowmore.cloud.base.server.annotation.Internal;
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

    @ApiOperation("试算")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "Authorization", value = "Bearer <access_token>", required = true, paramType = "header"),
    })
    @PostMapping("/charge")
    @RolesAllowed(RoleRegister.ROLE_COMMON_SERVICE)
    @Internal
    public VoidResponse billing(@RequestParam BillingRequest request) {

        return new VoidResponse();
    }

    @ApiOperation("计费")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "Authorization", value = "Bearer <access_token>", required = true, paramType = "header"),
    })
    @PostMapping("/trial")
    @RolesAllowed(RoleRegister.ROLE_COMMON_SERVICE)
    @Internal
    public VoidResponse trial(@RequestParam BillingRequest request) {

        return new VoidResponse();
    }

}
