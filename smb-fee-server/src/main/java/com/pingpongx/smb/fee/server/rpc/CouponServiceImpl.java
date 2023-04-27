package com.pingpongx.smb.fee.server.rpc;

import com.alibaba.fastjson.JSON;
import com.pingpongx.flowmore.cloud.base.server.annotation.Internal;
import com.pingpongx.flowmore.cloud.base.server.constants.RoleRegister;
import com.pingpongx.smb.fee.api.dtos.cmd.coupon.BatchCmd;
import com.pingpongx.smb.fee.api.dtos.cmd.coupon.CouponRequest;
import com.pingpongx.smb.fee.api.dtos.cmd.trade.BillingRequest;
import com.pingpongx.smb.fee.api.dtos.cmd.trade.OrderInfo;
import com.pingpongx.smb.fee.api.dtos.cmd.common.PayeeInfo;
import com.pingpongx.smb.fee.api.dtos.cmd.common.PayerInfo;
import com.pingpongx.smb.fee.api.dtos.cmd.common.RateInfo;
import com.pingpongx.smb.fee.api.dtos.resp.Bill;
import com.pingpongx.smb.fee.api.dtos.resp.BillList;
import com.pingpongx.smb.fee.api.feign.BillingServiceFeign;
import com.pingpongx.smb.fee.api.feign.CouponServiceFeign;
import com.pingpongx.smb.fee.dal.dataobject.BillingContextDo;
import com.pingpongx.smb.fee.dal.dataobject.BillingRequestDo;
import com.pingpongx.smb.fee.dal.dataobject.RepeatDo;
import com.pingpongx.smb.fee.dal.repository.BillingContextRepository;
import com.pingpongx.smb.fee.dal.repository.BillingRequestRepository;
import com.pingpongx.smb.fee.dal.repository.RepeatRepository;
import com.pingpongx.smb.fee.dependency.convert.BillingRequestConvert;
import com.pingpongx.smb.fee.dependency.convert.CouponRequestConvert;
import com.pingpongx.smb.fee.domain.convert.runtime.BillingContextConvert;
import com.pingpongx.smb.fee.domain.enums.FeePayer;
import com.pingpongx.smb.fee.domain.module.Request;
import com.pingpongx.smb.fee.domain.module.event.BillingRequestReceived;
import com.pingpongx.smb.fee.domain.module.event.BillingStage;
import com.pingpongx.smb.fee.domain.runtime.BillingContext;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.ApplicationContext;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.transaction.support.TransactionTemplate;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.security.RolesAllowed;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.CompletableFuture;


@Api(tags = "计费中心-new")
@RestController
@RequestMapping(value = CouponServiceImpl.BASE_PATH)
@Slf4j
@RequiredArgsConstructor
public class CouponServiceImpl implements CouponServiceFeign {

    private final RepeatRepository repeatRepository;
    private final BillingRequestRepository billingRequestRepository;
    private final BillingContextRepository contextRepository;
    private final TransactionTemplate txTemplate;
    private final ApplicationContext springContext;
    private final BillingContextConvert billingContextConvert;


    @RolesAllowed(RoleRegister.ROLE_COMMON_SERVICE)
    @Internal
    @ApiImplicitParams({
            @ApiImplicitParam(name = "Authorization", value = "service smb-fee@test", required = false, paramType = "header"),
            @ApiImplicitParam(name = "appId", value = "test@smb-fee", required = false, paramType = "header"),
    })
    public Bill billing(@RequestBody CouponRequest request) {
        RepeatDo repeatDo = RepeatDo.builder().repeatKey(request.identify()).scope(request.getClass().getName()).build();
        repeatDo.setCreatedBy("SYSTEM");
        repeatDo.setUpdatedBy("SYSTEM");
        repeatDo.setCreated(new Date(request.getBillingTime()));
        BillingContext context = new BillingContext();
        context.setRequest(Request.from(request));
        context.setTrial(false);
        CompletableFuture<BillingContext> future = new CompletableFuture<>();
        BillingContextDo contextDo = billingContextConvert.toDo(context);
        BillingRequestDo requestDo = CouponRequestConvert.toDo(request);
        try {
            Long id = txTemplate.execute(transactionStatus -> {
                repeatRepository.save(repeatDo);
                billingRequestRepository.save(requestDo);
                contextRepository.save(contextDo);
                return contextDo.getId();
            });
            context.setId(id);
        } catch (DuplicateKeyException e) {
            BillingContextDo retDo = contextRepository.findByRepeatKey(request.identify());
            context = billingContextConvert.toContext(retDo);
        }
        BillingStage stage = context.resume(future);
        springContext.publishEvent(stage);
        //处理同步返回
        try {
            context = future.get();
        }  catch (Exception e){
            log.error(e.getMessage(),e);
        }
        Bill resp = context.getBill();
        return resp;
    }

    @Override
    public BillList batchTrial(BatchCmd request) {
        return null;
    }

    @Override
    public BillList batchBilling(BatchCmd request) {
        return null;
    }

    @ApiImplicitParams({
            @ApiImplicitParam(name = "Authorization", value = "service smb-fee@test", required = false, paramType = "header"),
            @ApiImplicitParam(name = "appId", value = "test@smb-fee", required = false, paramType = "header"),
    })
    @RolesAllowed(RoleRegister.ROLE_COMMON_SERVICE)
    @Internal
    public Bill trial(@RequestBody CouponRequest request) {
        BillingContext context = new BillingContext();
        context.setRequest(Request.from(request));
        context.setTrial(true);
        CompletableFuture<BillingContext> future = new CompletableFuture<>();
        BillingRequestReceived stage = (BillingRequestReceived) context.resume(future);
        springContext.publishEvent(stage);
        //处理同步返回
        try {
            context = future.get();
        }  catch (Exception e){
            log.error(e.getMessage(),e);
        }
        Bill resp = context.getBill();
        return resp;
    }


}
