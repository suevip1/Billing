package com.pingpongx.smb.fee.server.rpc;

import com.pingpongx.flowmore.cloud.base.server.annotation.Internal;
import com.pingpongx.flowmore.cloud.base.server.constants.RoleRegister;
import com.pingpongx.smb.fee.domain.FeeServiceFeignService;
import com.pingpongx.smb.fee.common.dto.OrderInfoDTO;
import com.pingpongx.smb.fee.common.resp.FeeConfigResponse;
import com.pingpongx.smb.fee.server.service.FeeConfigService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.security.RolesAllowed;

/**
 * @author wangcheng
 * @Description TODO
 * @createTime 2022年07月02日 16:34:00
 */
@Api(tags = "RPC端-费率服务")
@RestController
@RequestMapping(value = FeeServiceFeignService.BASE_PATH)
public class FeeOrderFeignServiceImpl implements FeeServiceFeignService {

    @Autowired
    private FeeConfigService feeConfigService;

    @Internal
    @RolesAllowed(RoleRegister.ROLE_COMMON_SERVICE)
    @Override
    @ApiImplicitParams({
            @ApiImplicitParam(name = "Authorization", value = "service smb-fee@test", required = false, paramType = "header"),
            @ApiImplicitParam(name = "appId", value = "test@smb-fee", required = false, paramType = "header"),
    })
    public FeeConfigResponse getOrderFee(OrderInfoDTO request) {
        return feeConfigService.getOrderFee(request);
    }
}
