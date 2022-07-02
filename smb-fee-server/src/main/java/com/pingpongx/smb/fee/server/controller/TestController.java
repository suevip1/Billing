package com.pingpongx.smb.fee.server.controller;

import com.pingpongx.flowmore.cloud.base.commom.constants.VoidResponse;
import com.pingpongx.flowmore.cloud.base.server.annotation.NoAuth;
import com.pingpongx.flowmore.cloud.base.server.constants.RoleRegister;
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


@Api(tags = "测试查询")
@RestController
@RequestMapping(value = BASE_PATH + "/test")
@Slf4j
public class TestController {

    @ApiOperation("测试查询")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "Authorization", value = "Bearer <access_token>", required = true, paramType = "header"),
    })
    @PostMapping("/v1")
    @NoAuth
    @RolesAllowed(RoleRegister.ROLE_COMMON_SERVICE)
    public VoidResponse valid_a(@RequestParam String clientId) {
        return new VoidResponse();
    }

}
