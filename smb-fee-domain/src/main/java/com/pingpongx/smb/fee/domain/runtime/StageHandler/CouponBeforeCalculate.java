package com.pingpongx.smb.fee.domain.runtime.StageHandler;

import com.pingpongx.smb.fee.dal.dataobject.BillingContextDo;
import com.pingpongx.smb.fee.dal.repository.BillingContextRepository;
import com.pingpongx.smb.fee.domain.convert.runtime.BillingContextConvert;
import com.pingpongx.smb.fee.domain.module.event.BillingRequestReceived;
import com.pingpongx.smb.fee.domain.module.event.MatchParamCompleted;
import lombok.RequiredArgsConstructor;
import org.springframework.context.ApplicationContext;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CouponBeforeCalculate {
    private final ApplicationContext applicationContext;

    @EventListener
    void persistenceRequestReceived(MatchParamCompleted requestReceived) {
//        BillingContextDo dos = BillingContextConvert.toDo(requestReceived.getContext());
//        repository.saveOrUpdate(dos);

    }
}
