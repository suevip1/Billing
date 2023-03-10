package com.pingpongx.smb.fee.domain.StageHandler;

import com.pingpongx.smb.fee.dal.dataobject.BillingContextDo;
import com.pingpongx.smb.fee.dal.repository.BillingContextRepository;
import com.pingpongx.smb.fee.dependency.convert.BillingContextConvert;
import com.pingpongx.smb.fee.domain.module.event.BillingRequestReceived;
import lombok.RequiredArgsConstructor;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class BillingContextAsyncPersistence {
    private final BillingContextRepository repository;

    @EventListener
    void persistenceRequestReceived(BillingRequestReceived requestReceived) {
        BillingContextDo dos = BillingContextConvert.toDo(requestReceived.getContext());
        repository.saveOrUpdate(dos);
    }
}
