package com.pingpongx.smb.fee.server.rpc;

import com.pingpongx.flowmore.cloud.base.server.annotation.Internal;
import com.pingpongx.flowmore.cloud.base.server.constants.RoleRegister;
import com.pingpongx.smb.fee.api.dtos.BillingDetail;
import com.pingpongx.smb.fee.api.dtos.cmd.BillingRequest;
import com.pingpongx.smb.fee.api.feign.BillingServiceFeign;
import com.pingpongx.smb.fee.dal.dataobject.BillingRequestDo;
import com.pingpongx.smb.fee.dal.dataobject.RepeatDo;
import com.pingpongx.smb.fee.dal.repository.BillingRequestRepository;
import com.pingpongx.smb.fee.dal.repository.RepeatRepository;
import com.pingpongx.smb.fee.domain.runtime.BillingContext;
import com.pingpongx.smb.fee.server.utils.convert.BillingRequestConvert;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.ApplicationContext;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.transaction.support.TransactionTemplate;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.security.RolesAllowed;

import java.util.concurrent.CompletableFuture;


@Api(tags = "计费中心-new")
@RestController
@RequestMapping(value = BillingServiceFeign.BASE_PATH)
@Slf4j
@RequiredArgsConstructor
public class BillingServiceImpl implements BillingServiceFeign{

    private final RepeatRepository repeatRepository;
    private final BillingRequestRepository billingRequestRepository;
    private final TransactionTemplate txTemplate;
    private final ApplicationContext springContext;

    @RolesAllowed(RoleRegister.ROLE_COMMON_SERVICE)
    @Internal
    @ApiImplicitParams({
            @ApiImplicitParam(name = "Authorization", value = "service smb-fee@test", required = false, paramType = "header"),
            @ApiImplicitParam(name = "appId", value = "test@smb-fee", required = false, paramType = "header"),
    })
    public BillingDetail billing(@RequestParam BillingRequest request) {
        RepeatDo repeatDo = RepeatDo.builder().repeatKey(request.identify()).scope(request.getClass().getName()).build();
        BillingRequest billingRequest = new BillingRequest();

        try{
            txTemplate.executeWithoutResult(transactionStatus -> {
                repeatRepository.save(repeatDo);
                billingRequestRepository.save(BillingRequestConvert.toDo(request));
            });
        }catch (DuplicateKeyException e){
            BillingRequestDo ret = billingRequestRepository.findByRepeatKey(request.identify());
        }


        BillingContext context = new BillingContext();
        context.setRequest(request);
        context.setTrial(false);
        CompletableFuture<BillingContext> future = new CompletableFuture<>();
        context.setFuture(future);
        springContext.publishEvent(context);

        context = future.join();
        BillingDetail resp = new BillingDetail();
        resp.setFees(context.getFeeResult());
        resp.setExpenses(context.getExpense());
        return resp;
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
