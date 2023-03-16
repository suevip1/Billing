package com.pingpongx.smb.fee.domain.client;

import com.google.common.collect.Lists;
import com.pingpongx.business.common.dto.Money;
import com.pingpongx.mid.open.sdk.core.client.core.PPClient;
import com.pingpongx.mid.open.sdk.exception.SDKException;
import com.pingpongx.mid.open.sdk.gen.common.v1_0_0.exchangerate.model.PpExchangeExecuteResult;
import com.pingpongx.mid.open.sdk.gen.trade.v1_0_0.model.PpPayResult;
import com.pingpongx.mid.open.sdk.gen.trade.v1_0_0.model.PpTransferResult;
import com.pingpongx.smb.fee.MockedTest;
import com.pingpongx.smb.trade.api.dto.*;
import com.pingpongx.smb.trade.api.enums.*;
import com.pingpongx.smb.trade.api.exception.SmbTradeException;
import com.pingpongx.smb.trade.dependency.client.MidOpenClient;
import org.junit.jupiter.api.*;
import org.mockito.ArgumentMatchers;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;

import java.math.BigDecimal;
import java.util.Collections;
import java.util.Date;


public class MidOpenClientTest extends MockedTest {
    @InjectMocks
    MidOpenClient midOpenClient;

    @Mock
    private PPClient ppClient;

    @Test
    public void testTransfer() {
        TradeOrderDTO tradeOrderDTO = new TradeOrderDTO();
        // 订单基础信息
        tradeOrderDTO.setOrderId("TR2022070934674345005");
        tradeOrderDTO.setAmount(Money.builder().amount(BigDecimal.valueOf(10)).currency("CNY").build());
        tradeOrderDTO.setBizOrderId("TR022070934674345005");
        tradeOrderDTO.setBu(BuTypeEnum.SMB.name());
        tradeOrderDTO.setSubBu(BuTypeEnum.SMB.name());
        tradeOrderDTO.setMetaClientId("METAGO91220630038561170");
        tradeOrderDTO.setClientId("1034274324345");
        tradeOrderDTO.setReference("与中台联调");
        tradeOrderDTO.setRelateOrderId("TR2022070934674345005");
        tradeOrderDTO.setFailReason(null);
        tradeOrderDTO.setOrderType(OrderTypeEnum.TRANSFER_OUT.name());
        tradeOrderDTO.setOrderStatus(OrderStatusEnum.SUCCESS.name());
        tradeOrderDTO.setBizOrderType("TRANSFER");
        tradeOrderDTO.setFinishTime(null);
        tradeOrderDTO.setPayoutId(null);
        tradeOrderDTO.setRemark("与中台联调呀");
        tradeOrderDTO.setExtraInfo(Collections.singletonMap("123", "456"));
        AccountPayInfo accountPayInfo = new AccountPayInfo();

        // 支付账户详细信息
        accountPayInfo.setAccountId("2022070800200601017307");
        accountPayInfo.setSubOrderId("SUB_TR2022070934674345005");
        accountPayInfo.setAccountType("WALLET");
        accountPayInfo.setPayAmount(Money.builder().amount(BigDecimal.valueOf(10)).currency("CNY").build());
        accountPayInfo.setExchangeRate(BigDecimal.ONE);
        accountPayInfo.setTargetMoney(Money.builder().amount(BigDecimal.valueOf(9)).currency("CNY").build());
        accountPayInfo.setReceiveInAmount(Money.builder().amount(BigDecimal.valueOf(9)).currency("CNY").build());
        // 费率信息
        FeeInfo fixFee = new FeeInfo();
        fixFee.setFee(Money.builder().amount(BigDecimal.valueOf(1)).currency("CNY").build());
        fixFee.setFeeType(FeeType.SMB_FIX.getCode());
        fixFee.setRate(BigDecimal.valueOf(0.01));
        FeeInfo rateFee = new FeeInfo();
        rateFee.setFee(Money.builder().amount(BigDecimal.valueOf(1)).currency("CNY").build());
        rateFee.setFeeType(FeeType.SMB_PERCENT.getCode());
        rateFee.setRate(BigDecimal.valueOf(0.01));
        accountPayInfo.setFees(Lists.newArrayList(fixFee, rateFee));
        // 优惠信息
        DiscountInfo discountInfo = new DiscountInfo();
        discountInfo.setDiscountType("bonus");
        discountInfo.setRemark("测试");
        discountInfo.setAmount(Money.builder().amount(BigDecimal.valueOf(1)).currency("CNY").build());
        accountPayInfo.setDiscounts(Lists.newArrayList(discountInfo));
        tradeOrderDTO.setAccountPayInfos(Lists.newArrayList(accountPayInfo));

        // 收款人信息
        PayeeInfo payeeInfo = new PayeeInfo();
        payeeInfo.setTargetMetaClientId("c123");
        payeeInfo.setTargetBu(BuTypeEnum.SMB.name());
        payeeInfo.setTargetAccountId("2022070800200301016914");
        payeeInfo.setTargetBuClientId("104324235234234");
        payeeInfo.setReceiveType(ReceiveType.INNER_ACCOUNT.name());
        tradeOrderDTO.setPayeeInfo(payeeInfo);

        try {
            PpTransferResult result = new PpTransferResult();
            result.setCode(40005);
            result.setMessage("转账冻结失败.");
            //返回失败
            Mockito.when(ppClient.invoke(ArgumentMatchers.any(), ArgumentMatchers.any(), ArgumentMatchers.eq(PpTransferResult.class))).thenReturn(result);
            midOpenClient.froze(tradeOrderDTO);
        } catch (Exception e) {
            Assertions.assertTrue(e instanceof SmbTradeException);
            Assertions.assertTrue(((SmbTradeException) e).getMsg().contains("转账冻结失败"));
        }

        try {
            PpTransferResult result = new PpTransferResult();
            result.setCode(200);
            //返回成功
            Mockito.when(ppClient.invoke(ArgumentMatchers.any(), ArgumentMatchers.any(), ArgumentMatchers.eq(PpTransferResult.class))).thenReturn(result);
            midOpenClient.froze(tradeOrderDTO);
        } catch (Exception e) {
            Assertions.fail();
        }

        tradeOrderDTO.setOrderType(OrderTypeEnum.SUPPLIER.name());
        try {
            PpPayResult result = new PpPayResult();
            result.setCode(40005);
            result.setMessage("转账冻结失败.");
            //返回失败
            Mockito.when(ppClient.invoke(ArgumentMatchers.any(), ArgumentMatchers.any(), ArgumentMatchers.eq(PpPayResult.class))).thenReturn(result);
            midOpenClient.froze(tradeOrderDTO);
        } catch (Exception e) {
            Assertions.assertTrue(e instanceof SmbTradeException);
            Assertions.assertTrue(((SmbTradeException) e).getMsg().contains("转账冻结失败"));
        }

        try {
            PpPayResult result = new PpPayResult();
            result.setCode(200);
            //返回成功
            Mockito.when(ppClient.invoke(ArgumentMatchers.any(), ArgumentMatchers.any(), ArgumentMatchers.eq(PpPayResult.class))).thenReturn(result);
            midOpenClient.froze(tradeOrderDTO);
        } catch (Exception e) {
            Assertions.fail();
        }
    }


    @Test
    public void testTransferSuccess() {
        TradeOrderDTO tradeOrderDTO = new TradeOrderDTO();
        // 订单基础信息
        tradeOrderDTO.setOrderId("TR2022070934674345005");
        tradeOrderDTO.setAmount(Money.builder().amount(BigDecimal.valueOf(10)).currency("CNY").build());
        tradeOrderDTO.setBizOrderId("TR022070934674345005");
        tradeOrderDTO.setBu(BuTypeEnum.SMB.name());
        tradeOrderDTO.setSubBu(BuTypeEnum.SMB.name());
        tradeOrderDTO.setMetaClientId("METAGO91220630038561170");
        tradeOrderDTO.setClientId("1034274324345");
        tradeOrderDTO.setReference("与中台联调");
        tradeOrderDTO.setRelateOrderId("TR2022070934674345005");
        tradeOrderDTO.setFailReason(null);
        tradeOrderDTO.setOrderType(OrderTypeEnum.TRANSFER_OUT.name());
        tradeOrderDTO.setOrderStatus(OrderStatusEnum.SUCCESS.name());
        tradeOrderDTO.setBizOrderType("TRANSFER");
        tradeOrderDTO.setFinishTime(new Date().getTime());
        tradeOrderDTO.setPayoutId("P120220709140634899549000005");
        tradeOrderDTO.setRemark("与中台联调呀");
        AccountPayInfo accountPayInfo = new AccountPayInfo();

        // 支付账户详细信息
        accountPayInfo.setAccountId("2022070800200601017307");
        accountPayInfo.setSubOrderId("SUB_TR2022070934674345005");
        accountPayInfo.setAccountType("WALLET");
        accountPayInfo.setPayAmount(Money.builder().amount(BigDecimal.valueOf(10)).currency("CNY").build());
        accountPayInfo.setExchangeRate(BigDecimal.ONE);
        accountPayInfo.setTargetMoney(Money.builder().amount(BigDecimal.valueOf(9)).currency("CNY").build());
        accountPayInfo.setReceiveInAmount(Money.builder().amount(BigDecimal.valueOf(9)).currency("CNY").build());
        // 费率信息
        FeeInfo fixFee = new FeeInfo();
        fixFee.setFee(Money.builder().amount(BigDecimal.valueOf(1)).currency("CNY").build());
        fixFee.setFeeType("base");
        fixFee.setRate(BigDecimal.valueOf(0.01));
        FeeInfo rateFee = new FeeInfo();
        rateFee.setFee(Money.builder().amount(BigDecimal.valueOf(1)).currency("CNY").build());
        rateFee.setFeeType("service");
        rateFee.setRate(BigDecimal.valueOf(0.01));
        accountPayInfo.setFees(Lists.newArrayList(fixFee, rateFee));
        // 优惠信息
        DiscountInfo discountInfo = new DiscountInfo();
        discountInfo.setDiscountType("bonus");
        discountInfo.setRemark("测试");
        discountInfo.setAmount(Money.builder().amount(BigDecimal.valueOf(1)).currency("CNY").build());
        accountPayInfo.setDiscounts(Lists.newArrayList(discountInfo));
        tradeOrderDTO.setAccountPayInfos(Lists.newArrayList(accountPayInfo));

        // 收款人信息
        PayeeInfo payeeInfo = new PayeeInfo();
        payeeInfo.setTargetMetaClientId("c123");
        payeeInfo.setTargetBu(BuTypeEnum.SMB.name());
        payeeInfo.setTargetAccountId("2022070800200301016914");
        payeeInfo.setTargetBuClientId("104324235234234");
        payeeInfo.setReceiveType(ReceiveType.INNER_ACCOUNT.name());
        tradeOrderDTO.setPayeeInfo(payeeInfo);

        try {
            PpTransferResult result = new PpTransferResult();
            result.setCode(40005);
            result.setMessage("转账冻结失败.");
            //返回失败
            Mockito.when(ppClient.invoke(ArgumentMatchers.any(), ArgumentMatchers.any(), ArgumentMatchers.eq(PpTransferResult.class))).thenReturn(result);
            boolean ret = midOpenClient.unfreeze(tradeOrderDTO);
            Assertions.assertTrue(!ret);
        } catch (Exception e) {
            Assertions.fail();
        }

        try {
            PpTransferResult result = new PpTransferResult();
            result.setCode(200);
            //返回失败
            Mockito.when(ppClient.invoke(ArgumentMatchers.any(), ArgumentMatchers.any(), ArgumentMatchers.eq(PpTransferResult.class))).thenReturn(result);
            boolean ret = midOpenClient.unfreeze(tradeOrderDTO);
            Assertions.assertTrue(ret);
        } catch (Exception e) {
            Assertions.fail();
        }

        tradeOrderDTO.setOrderType(OrderTypeEnum.WITHDRAW.name());
        try {
            PpPayResult result = new PpPayResult();
            result.setCode(200);
            //返回失败
            Mockito.when(ppClient.invoke(ArgumentMatchers.any(), ArgumentMatchers.any(), ArgumentMatchers.eq(PpPayResult.class))).thenReturn(result);
            boolean ret = midOpenClient.unfreeze(tradeOrderDTO);
            Assertions.assertTrue(ret);
        } catch (Exception e) {
            Assertions.fail();
        }

        try {
            PpPayResult result = new PpPayResult();
            result.setCode(40005);
            result.setMessage("转账冻结失败.");
            //返回失败
            Mockito.when(ppClient.invoke(ArgumentMatchers.any(), ArgumentMatchers.any(), ArgumentMatchers.eq(PpPayResult.class))).thenReturn(result);
            boolean ret = midOpenClient.unfreeze(tradeOrderDTO);
            Assertions.assertTrue(!ret);
        } catch (Exception e) {
            Assertions.fail();
        }

        tradeOrderDTO.setOrderStatus(OrderStatusEnum.DISPATCH_FAIL.name());
        try {
            PpPayResult result = new PpPayResult();
            result.setCode(200);
            //返回失败
            Mockito.when(ppClient.invoke(ArgumentMatchers.any(), ArgumentMatchers.any(), ArgumentMatchers.eq(PpPayResult.class))).thenReturn(result);
            boolean ret = midOpenClient.unfreeze(tradeOrderDTO);
            Assertions.assertTrue(ret);
        } catch (Exception e) {
            Assertions.fail();
        }
    }

    @Test
    public void testFinishNotifySuccess() throws SDKException {
        PpExchangeExecuteResult result = new PpExchangeExecuteResult();
        result.setSuccess(true);

        TradeOrderDTO tradeOrderDTO = new TradeOrderDTO();
        tradeOrderDTO.setAccountReceiveInfo(new AccountReceiveInfo());

        Mockito.when(ppClient.invoke(ArgumentMatchers.any(), ArgumentMatchers.any(),
                ArgumentMatchers.eq(PpExchangeExecuteResult.class))).thenReturn(result);

        midOpenClient.finishNotify(tradeOrderDTO);
    }

    @Test
    public void testFinishNotifyException() throws SDKException {
        PpPayResult result = new PpPayResult();
        result.setSuccess(true);

        TradeOrderDTO tradeOrderDTO = new TradeOrderDTO();
        tradeOrderDTO.setAccountReceiveInfo(new AccountReceiveInfo());

        Mockito.when(ppClient.invoke(ArgumentMatchers.any(), ArgumentMatchers.any(),
                ArgumentMatchers.eq(PpPayResult.class))).thenReturn(result);

        Assertions.assertThrows(SmbTradeException.class, () -> {
            midOpenClient.finishNotify(tradeOrderDTO);
        });
    }

    @Test
    public void testFinishNotifyError() throws SDKException {
        PpExchangeExecuteResult result = new PpExchangeExecuteResult();
        result.setSuccess(false);

        TradeOrderDTO tradeOrderDTO = new TradeOrderDTO();
        tradeOrderDTO.setAccountReceiveInfo(new AccountReceiveInfo());

        Mockito.when(ppClient.invoke(ArgumentMatchers.any(), ArgumentMatchers.any(),
                ArgumentMatchers.eq(PpExchangeExecuteResult.class))).thenReturn(result);
        Assertions.assertThrows(SmbTradeException.class,
                () -> midOpenClient.finishNotify(tradeOrderDTO));
    }

    @Test
    public void testFinishNotifyError_2() throws SDKException {
        PpExchangeExecuteResult result = new PpExchangeExecuteResult();
        result.setSuccess(false);

        TradeOrderDTO tradeOrderDTO = new TradeOrderDTO();
        tradeOrderDTO.setAccountReceiveInfo(new AccountReceiveInfo());

        Mockito.when(ppClient.invoke(ArgumentMatchers.any(), ArgumentMatchers.any(),
                ArgumentMatchers.eq(PpExchangeExecuteResult.class))).thenReturn(null);
        Assertions.assertThrows(SmbTradeException.class,
                () -> midOpenClient.finishNotify(tradeOrderDTO));
    }
}
