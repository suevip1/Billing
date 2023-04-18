package com.pingpongx.smb.fee.domain.module.coupon;

import com.pingpongx.business.common.dto.Money;
import com.pingpongx.smb.asset.api.coupon.enums.CouponTypeEnum;
import com.pingpongx.smb.fee.api.dtos.cmd.CouponInfo;
import com.pingpongx.smb.fee.api.dtos.resp.CostItemResult;
import com.pingpongx.smb.fee.api.dtos.resp.CouponResult;
import com.pingpongx.smb.fee.api.dtos.resp.coupon.CouponAction;
import com.pingpongx.smb.fee.api.dtos.resp.coupon.CreditDecrease;
import com.pingpongx.smb.fee.domain.convert.ExchangeConvert;
import com.pingpongx.smb.fee.domain.enums.CurrencyType;
import com.pingpongx.smb.fee.domain.module.express.AXpB;
import com.pingpongx.smb.fee.domain.module.express.Expr;
import com.pingpongx.smb.fee.domain.module.express.Fixed;
import com.pingpongx.smb.fee.domain.module.express.Min;
import com.pingpongx.smb.fee.domain.runtime.BillingContext;
import com.pingpongx.smb.metadata.VariableDef;
import com.pingpongx.smb.metadata.properties.ContextVar;
import io.vavr.Tuple;
import io.vavr.Tuple2;
import io.vavr.Tuple3;
import javafx.util.Pair;
import lombok.Data;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Component
public class AmountOffHandler implements BeforeCalculateHandler {


    @Override
    public String supportedType() {
        return CouponTypeEnum.FEE_DEDUCTION.name();
    }

    public Tuple3<String,BigDecimal, CouponAction> couponParamFix(CouponInfo couponInfo, BillingContext context){
        Money sourceCredit = ExchangeConvert.convert(CurrencyType.valueOf(couponInfo.getCurrency()),CurrencyType.Source,context,couponInfo.getValidValue());
        BigDecimal couponValidAmount = sourceCredit.getAmount();

        BigDecimal amount = context.getRequest().getOrderInfo().getAmount();
        BigDecimal creditCost = couponValidAmount.min(amount);
        BigDecimal finalAmount = amount.subtract(creditCost);
        if (finalAmount.equals(couponValidAmount)){
            return Tuple.of("amount",finalAmount,CreditDecrease.of(couponInfo.getCurrency(), couponInfo.getValidValue()));
        }
        return Tuple.of("amount",finalAmount,CreditDecrease.of(sourceCredit.getCurrency(),creditCost));
    }

}
