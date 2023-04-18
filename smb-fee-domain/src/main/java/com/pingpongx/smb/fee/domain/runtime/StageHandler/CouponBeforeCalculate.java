package com.pingpongx.smb.fee.domain.runtime.StageHandler;

import com.pingpongx.smb.asset.api.coupon.enums.CouponTypeEnum;
import com.pingpongx.smb.fee.api.dtos.cmd.CouponInfo;
import com.pingpongx.smb.fee.api.dtos.resp.CouponResult;
import com.pingpongx.smb.fee.api.dtos.resp.coupon.CouponAction;
import com.pingpongx.smb.fee.api.dtos.resp.coupon.CreditDecrease;
import com.pingpongx.smb.fee.dal.dataobject.BillingContextDo;
import com.pingpongx.smb.fee.dal.repository.BillingContextRepository;
import com.pingpongx.smb.fee.domain.convert.runtime.BillingContextConvert;
import com.pingpongx.smb.fee.domain.module.coupon.BeforeCalculateHandler;
import com.pingpongx.smb.fee.domain.module.coupon.CouponHandlerFactory;
import com.pingpongx.smb.fee.domain.module.event.BillingRequestReceived;
import com.pingpongx.smb.fee.domain.module.event.CouponParamUpdated;
import com.pingpongx.smb.fee.domain.module.event.MatchCompleted;
import com.pingpongx.smb.fee.domain.module.event.MatchParamCompleted;
import com.pingpongx.smb.fee.domain.runtime.BillingContext;
import io.vavr.Tuple;
import io.vavr.Tuple2;
import io.vavr.Tuple3;
import lombok.RequiredArgsConstructor;
import org.springframework.context.ApplicationContext;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;

@Service
@RequiredArgsConstructor
public class CouponBeforeCalculate extends BizFlowNode{
    private final CouponHandlerFactory handlerFactory;

    @EventListener
    void couponParamFix(MatchCompleted matchCompleted) {
        BillingContext context = matchCompleted.getContext();
        context.getRequest().getCouponList();
        try{
            CouponParamUpdated paramUpdated = new CouponParamUpdated(context);
            List<CouponInfo> couponList = context.getRequest().getCouponList();
            couponList.stream().filter(c-> needProcess(c))
                    .map(couponInfo -> Tuple.of(couponInfo,((BeforeCalculateHandler)handlerFactory.getCouponHandler(couponInfo.getCouponType())).couponParamFix(couponInfo,context)))
                    .forEach(t->{
                        Tuple3<String,BigDecimal, CouponAction> tt = t._2();
                        context.getParams().put(tt._1()+"Origin",context.getParams().get(tt._1()));
                        context.getParams().put(tt._1(),tt._2().toString());
                        context.getBill().getExpense().add(CouponResult.of(t._1(), tt._3()));
                    });
            applicationContext.publishEvent(paramUpdated);
        }catch (Exception e){
            handleException(context,e);
        }

    }

    private boolean needProcess(CouponInfo couponInfo){
        if (CouponTypeEnum.AMOUNT_REDUCTION.name().equals(couponInfo.getCouponType())){
            return true;
        }
        return false;
    }
}
