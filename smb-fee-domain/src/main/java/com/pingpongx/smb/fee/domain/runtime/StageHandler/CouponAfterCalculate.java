package com.pingpongx.smb.fee.domain.runtime.StageHandler;

import com.pingpongx.smb.asset.api.coupon.enums.CouponTypeEnum;
import com.pingpongx.smb.fee.api.dtos.cmd.common.CouponInfo;
import com.pingpongx.smb.fee.domain.module.coupon.AfterCalculateHandler;
import com.pingpongx.smb.fee.domain.module.coupon.CouponHandlerFactory;
import com.pingpongx.smb.fee.domain.module.event.CalculateCompleted;
import com.pingpongx.smb.fee.domain.module.event.Finished;
import com.pingpongx.smb.fee.domain.runtime.BillingContext;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class CouponAfterCalculate extends BizFlowNode {
    private final CouponHandlerFactory handlerFactory;

    @EventListener
    void couponResultFix(CalculateCompleted calculateCompleted) {
        BillingContext context = calculateCompleted.getContext();
        List<CouponInfo> couponInfos = context.getRequest().getCouponList();
        if (couponInfos == null){
            Finished finished = new Finished(context);
            applicationContext.publishEvent(finished);
            return;
        }
        couponInfos.stream().filter(this::needProcess).forEach(couponInfo -> {
            AfterCalculateHandler handler = (AfterCalculateHandler)handlerFactory.getCouponHandler(couponInfo.getCouponType());
            handler.couponResultFix(couponInfo,context);
        });
        try{
            Finished finished = new Finished(context);
            applicationContext.publishEvent(finished);
        }catch (Exception e){
            log.error("error when couponResultFix.",e);
            handleException(context,e);
        }

    }

    private boolean needProcess(CouponInfo couponInfo){
        if (CouponTypeEnum.FEE_DEDUCTION.name().equals(couponInfo.getCouponType())||CouponTypeEnum.PAY_DEDUCTION.name().equals(couponInfo.getCouponType())){
            return true;
        }
        return false;
    }
}
