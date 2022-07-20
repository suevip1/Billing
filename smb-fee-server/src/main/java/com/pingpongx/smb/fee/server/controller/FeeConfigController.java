package com.pingpongx.smb.fee.server.controller;

import com.pingpongx.flowmore.cloud.base.server.annotation.NoAuth;
import com.pingpongx.smb.fee.common.dto.FeeConfigDTO;
import com.pingpongx.smb.fee.common.req.FeeConfigRequest;
import com.pingpongx.smb.fee.server.service.FeeConfigService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import static com.pingpongx.smb.fee.common.constants.FeeConstants.BASE_PATH;

/**
 * @author wangcheng
 * @Description TODO
 * @createTime 2022年06月30日 16:03:00
 */
@Api(tags = "费率配置")
@RestController
@RequestMapping(value = BASE_PATH + "/feeConfig")
@Slf4j
public class FeeConfigController {

    @Autowired
    private FeeConfigService feeConfigService;


    @ApiOperation("新增费率配置")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "Authorization", value = "Bearer <access_token>", required = false, paramType = "header"),
    })
    @PostMapping("/add")
    @RolesAllowed(RoleRegister.ROLE_COMMON_SERVICE)
    @Internal
    public void addFeeConfig(FeeConfigDTO dto) {
        feeConfigService.addFeeConfig(dto);
    }

    @ApiOperation("更新费率配置")
//    @ApiImplicitParams({
//            @ApiImplicitParam(name = "Authorization", value = "Bearer <access_token>", required = true, paramType = "header"),
//    })
    @PostMapping("/update")
    @RolesAllowed(RoleRegister.ROLE_COMMON_SERVICE)
    @Internal
    public void updateFeeConfig(FeeConfigDTO dto) {
        feeConfigService.updateFeeConfig(dto);
    }

    @ApiOperation("删除费率配置")
//    @ApiImplicitParams({
//            @ApiImplicitParam(name = "Authorization", value = "Bearer <access_token>", required = true, paramType = "header"),
//    })
    @PostMapping("/delete")
    @RolesAllowed(RoleRegister.ROLE_COMMON_SERVICE)
    @Internal
    public void deleteFeeConfig(Integer id) {
        feeConfigService.deleteFeeConfig(id);
    }


    @ApiOperation("分页查询费率配置")
//    @ApiImplicitParams({
//            @ApiImplicitParam(name = "Authorization", value = "Bearer <access_token>", required = true, paramType = "header"),
//    })
    @GetMapping("/page")
    @RolesAllowed(RoleRegister.ROLE_COMMON_SERVICE)
    @Internal
    public Object getFeeConfigPage(FeeConfigRequest request) {
        return feeConfigService.getFeeConfigPage(request);
    }

}
