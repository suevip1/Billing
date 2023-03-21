package com.pingpongx.smb.fee.domain.runtime.StageHandler;

import com.pingpongx.smb.fee.dal.dataobject.BillingContextDo;
import com.pingpongx.smb.fee.dal.repository.BillingContextRepository;
import com.pingpongx.smb.fee.domain.convert.runtime.BillingContextConvert;
import com.pingpongx.smb.fee.domain.module.event.BillingRequestReceived;
import com.pingpongx.smb.fee.domain.module.event.CouponParamUpdated;
import com.pingpongx.smb.fee.domain.module.event.MatchCompleted;
import com.pingpongx.smb.fee.domain.module.event.MatchParamCompleted;
import com.pingpongx.smb.fee.domain.runtime.BillingContext;
import lombok.RequiredArgsConstructor;
import org.springframework.context.ApplicationContext;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Service;

@Service
public class CouponBeforeCalculate extends BizFlowNode{
    @EventListener
    void couponParamFix(MatchCompleted matchCompleted) {
        BillingContext context = matchCompleted.getContext();
        try{
            CouponParamUpdated paramUpdated = new CouponParamUpdated(context);
            applicationContext.publishEvent(paramUpdated);
        }catch (Exception e){
            handleException(context,e);
        }

    }
}
