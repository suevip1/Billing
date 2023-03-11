package com.pingpongx.smb.fee.domain.runtime.StageHandler;

import com.pingpongx.smb.fee.api.dtos.cmd.BillingRequest;
import com.pingpongx.smb.fee.dal.dataobject.BillingContextDo;
import com.pingpongx.smb.fee.dal.repository.BillingContextRepository;
import com.pingpongx.smb.fee.domain.convert.runtime.BillingContextConvert;
import com.pingpongx.smb.fee.domain.module.event.BillingRequestReceived;
import com.pingpongx.smb.fee.domain.runtime.BillingContext;
import lombok.RequiredArgsConstructor;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class MatchParamPrepare {
    private final BillingContextRepository repository;

    @EventListener
    void persistenceRequestReceived(BillingRequestReceived requestReceived) {
        BillingContext context = requestReceived.getContext();
        BillingRequest request = context.getRequest();

//        EngineTODO

        BillingContextDo dos = BillingContextConvert.toDo(requestReceived.getContext());
        repository.saveOrUpdate(dos);
    }
}
