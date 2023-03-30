package com.pingpongx.smb.fee.server.rpc;

import com.alibaba.fastjson.JSON;
import com.pingpongx.flowmore.cloud.base.server.annotation.Internal;
import com.pingpongx.flowmore.cloud.base.server.constants.RoleRegister;
import com.pingpongx.smb.export.globle.Engine;
import com.pingpongx.smb.export.module.persistance.RuleDto;
import com.pingpongx.smb.fee.api.dtos.cmd.BillingRequest;
import com.pingpongx.smb.fee.api.dtos.cmd.OrderInfo;
import com.pingpongx.smb.fee.api.dtos.cmd.PayeeInfo;
import com.pingpongx.smb.fee.api.dtos.cmd.PayerInfo;
import com.pingpongx.smb.fee.api.dtos.resp.Bill;
import com.pingpongx.smb.fee.api.feign.BillingConfigServiceFeign;
import com.pingpongx.smb.fee.api.feign.BillingServiceFeign;
import com.pingpongx.smb.fee.dal.dataobject.BillingContextDo;
import com.pingpongx.smb.fee.dal.dataobject.BillingRequestDo;
import com.pingpongx.smb.fee.dal.dataobject.RepeatDo;
import com.pingpongx.smb.fee.dal.repository.BillingContextRepository;
import com.pingpongx.smb.fee.dal.repository.BillingRequestRepository;
import com.pingpongx.smb.fee.dal.repository.RepeatRepository;
import com.pingpongx.smb.fee.dependency.convert.BillingRequestConvert;
import com.pingpongx.smb.fee.domain.convert.runtime.BillingContextConvert;
import com.pingpongx.smb.fee.domain.module.event.BillingRequestReceived;
import com.pingpongx.smb.fee.domain.module.event.BillingStage;
import com.pingpongx.smb.fee.domain.rules.Engines;
import com.pingpongx.smb.fee.domain.runtime.BillingContext;
import com.pingpongx.smb.metadata.dto.VariableDefDto;
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


@Api(tags = "计费中心-配置")
@RestController
@RequestMapping(value = BillingConfigServiceFeign.BASE_PATH)
@Slf4j
@RequiredArgsConstructor
public class BillingConfigServiceImpl implements BillingConfigServiceFeign  {

    private final Engines engines;



    @Override
    public void refresh(String costNode) {
        engines.rebuildEngine(costNode);
    }

    @Override
    public Bill saveOrUpdateVariable(VariableDefDto request) {
        return null;
    }


}
