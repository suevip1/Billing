package com.pingpongx.smb.fee.server.rpc;

import com.pingpongx.flowmore.cloud.base.server.annotation.Internal;
import com.pingpongx.flowmore.cloud.base.server.annotation.NoAuth;
import com.pingpongx.flowmore.cloud.base.server.constants.RoleRegister;
import com.pingpongx.smb.fee.api.feign.FxExchangeFeignService;
import com.pingpongx.smb.fee.common.resp.ExchangeRateResponse;
import com.pingpongx.smb.fee.common.resp.FinalExchangeRateResponse;
import com.pingpongx.smb.fee.server.service.FxExchangeService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.security.RolesAllowed;
import java.util.List;

/**
 * @author wangcheng
 * @Description TODO
 * @createTime 2022年07月05日 11:44:00
 */
@Api(tags = "RPC端-汇率服务")
@RestController
@RequestMapping(value = FxExchangeFeignService.BASE_PATH)
public class FxExchangeFeignServiceImpl implements FxExchangeFeignService {


    @Autowired
    private FxExchangeService fxExchangeService;

    @NoAuth
    @Override
    @ApiImplicitParams({
            @ApiImplicitParam(name = "Authorization", value = "smb-fee", required = false, paramType = "header"),
            @ApiImplicitParam(name = "appId", value = "smb-fee", required = false, paramType = "header"),
    })
    public FinalExchangeRateResponse getAmountEstimateExchangeRate() {
        List<ExchangeRateResponse> list = fxExchangeService.getAmountEstimateExchangeRate();
        FinalExchangeRateResponse response = FinalExchangeRateResponse.builder().data(list).build();
        if (!CollectionUtils.isEmpty(list)) {
            response.setUpdateTime(list.get(0).getUpdateTime());
        }
        return response;
    }
}
