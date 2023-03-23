package com.pingpongx.smb.fee.domain.runtime.StageHandler;

import com.alibaba.fastjson.JSON;
import com.pingpongx.smb.fee.api.dtos.cmd.BillingRequest;
import com.pingpongx.smb.fee.dal.dataobject.BillingContextDo;
import com.pingpongx.smb.fee.dal.dataobject.ConfiguredVariableDef;
import com.pingpongx.smb.fee.dal.repository.BillingContextRepository;
import com.pingpongx.smb.fee.dal.repository.VariableDefRepository;
import com.pingpongx.smb.fee.domain.convert.runtime.BillingContextConvert;
import com.pingpongx.smb.fee.domain.factory.variable.VariableFactory;
import com.pingpongx.smb.fee.domain.module.event.BillingRequestReceived;
import com.pingpongx.smb.fee.domain.module.event.MatchParamCompleted;
import com.pingpongx.smb.fee.domain.rules.Engines;
import com.pingpongx.smb.fee.domain.runtime.BillingContext;
import io.vavr.Tuple;
import io.vavr.Tuple2;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.ApplicationContext;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Slf4j
public class MatchParamPrepare extends BizFlowNode{
    private final Engines engines;
    private final VariableDefRepository variableDefRepository;

    @EventListener
    void paramPrepare(BillingRequestReceived requestReceived) {
        BillingContext context = requestReceived.getContext();
        BillingRequest request = context.getRequest();
        log.info("param prepare start. input:" + JSON.toJSONString(request));
        try{
            if (request.valid() != null){
                throw new RuntimeException(request.valid());
            }
            Set<String> vars = engines.variablesToPrepare(request.getCostNodeCode());
            Map<String,String> params = context.getParams();
            params.put("billingTime",context.getRequest().getBillingTime().toString());

            if (vars.isEmpty()){
                MatchParamCompleted matchParamCompleted = new MatchParamCompleted(context);
                applicationContext.publishEvent(matchParamCompleted);
                return;
            }

            List<ConfiguredVariableDef> defs = variableDefRepository.getByCode(vars);
            if (defs.size()!=vars.size()){
                Set<String> found = defs.stream().map(def -> def.getCode()).collect(Collectors.toSet());
                vars.removeAll(found);
                String notFound = vars.stream().collect(Collectors.joining(","));
                throw new RuntimeException("configuration error. variables at bellow can't be found.\n"+notFound);
            }

            Map<String,String> p = defs.stream().map(def -> VariableFactory.load(def))
                    .map(v-> Tuple.of(v.getCode(),v.extractor().doExtract(v,request)))
                    .collect(Collectors.toMap(Tuple2::_1,Tuple2::_2));
            params.putAll(p);
            MatchParamCompleted matchParamCompleted = new MatchParamCompleted(context);
            applicationContext.publishEvent(matchParamCompleted);
        }catch (Exception e){
            handleException(context,e);
        }

    }
}
