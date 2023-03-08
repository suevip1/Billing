package com.pingpongx.smb.fee.server.rpc;

import com.pingpongx.flowmore.cloud.base.commom.constants.VoidResponse;
import com.pingpongx.flowmore.cloud.base.server.annotation.Internal;
import com.pingpongx.flowmore.cloud.base.server.constants.RoleRegister;
import com.pingpongx.smb.fee.api.dtos.BillingDetail;
import com.pingpongx.smb.fee.api.dtos.cmd.BillingRequest;
import com.pingpongx.smb.fee.api.feign.BillingServiceFeign;
import com.pingpongx.smb.fee.api.feign.FeeServiceFeignService;
import com.pingpongx.smb.fee.dal.dataobject.BillingRequestDo;
import com.pingpongx.smb.fee.dal.dataobject.RepeatDo;
import com.pingpongx.smb.fee.dal.repository.BillingRequestRepository;
import com.pingpongx.smb.fee.dal.repository.RepeatRepository;
import com.pingpongx.smb.fee.server.utils.ConvertUtil;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.transaction.support.TransactionTemplate;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.security.RolesAllowed;

import static com.pingpongx.smb.fee.common.constants.FeeConstants.BASE_PATH;


@Api(tags = "计费中心-new")
@RestController
@RequestMapping(value = BillingServiceFeign.BASE_PATH)
@Slf4j
@RequiredArgsConstructor
public class BillingServiceImpl implements BillingServiceFeign{

    private final RepeatRepository repeatRepository;
    private final BillingRequestRepository billingRequestRepository;
    private final TransactionTemplate txTemplate;

    @RolesAllowed(RoleRegister.ROLE_COMMON_SERVICE)
    @Internal
    @ApiImplicitParams({
            @ApiImplicitParam(name = "Authorization", value = "service smb-fee@test", required = false, paramType = "header"),
            @ApiImplicitParam(name = "appId", value = "test@smb-fee", required = false, paramType = "header"),
    })
    public BillingDetail billing(@RequestParam BillingRequest request) {
        RepeatDo repeatDo = RepeatDo.builder().repeatKey(request.repeatKey()).scope(request.getClass().getName()).build();
        BillingRequest billingRequest = new BillingRequest();

        txTemplate.execute(transactionStatus -> {
            repeatRepository.save(repeatDo);
            billingRequestRepository.save(ConvertUtil.toDo(request, BillingRequestDo.class ,(requestDo)->{

            }));
            return null;
        });


        return null;
    }

    @ApiImplicitParams({
            @ApiImplicitParam(name = "Authorization", value = "service smb-fee@test", required = false, paramType = "header"),
            @ApiImplicitParam(name = "appId", value = "test@smb-fee", required = false, paramType = "header"),
    })
    @RolesAllowed(RoleRegister.ROLE_COMMON_SERVICE)
    @Internal
    public BillingDetail trial(@RequestParam BillingRequest request) {
        return null;
    }

}
