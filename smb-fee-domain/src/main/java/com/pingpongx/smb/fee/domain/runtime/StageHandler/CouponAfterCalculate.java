package com.pingpongx.smb.fee.domain.runtime.StageHandler;

import com.pingpongx.smb.asset.api.coupon.enums.CouponTypeEnum;
import com.pingpongx.smb.fee.api.dtos.cmd.CouponInfo;
import com.pingpongx.smb.fee.api.dtos.resp.CostItemResult;
import com.pingpongx.smb.fee.api.dtos.resp.CouponResult;
import com.pingpongx.smb.fee.api.dtos.resp.coupon.CouponAction;
import com.pingpongx.smb.fee.dal.dataobject.BillingContextDo;
import com.pingpongx.smb.fee.dal.repository.BillingContextRepository;
import com.pingpongx.smb.fee.domain.convert.runtime.BillingContextConvert;
import com.pingpongx.smb.fee.domain.module.coupon.AfterCalculateHandler;
import com.pingpongx.smb.fee.domain.module.coupon.CouponHandlerFactory;
import com.pingpongx.smb.fee.domain.module.event.BillingRequestReceived;
import com.pingpongx.smb.fee.domain.module.event.CalculateCompleted;
import com.pingpongx.smb.fee.domain.module.event.CouponParamUpdated;
import com.pingpongx.smb.fee.domain.module.event.Finished;
import com.pingpongx.smb.fee.domain.runtime.BillingContext;
import io.vavr.Tuple2;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Service;

import java.util.Map;
import java.util.concurrent.CompletableFuture;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Slf4j
public class CouponAfterCalculate extends BizFlowNode {
    private final CouponHandlerFactory handlerFactory;

    @EventListener
    void couponResultFix(CalculateCompleted calculateCompleted) {
        BillingContext context = calculateCompleted.getContext();
        context.getRequest().getCouponList().stream().filter(this::needProcess).forEach(couponInfo -> {
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
