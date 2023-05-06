package com.pingpongx.smb.fee.domain.service;

import com.alibaba.fastjson.JSON;
import com.pingpongx.smb.fee.MockedTest;
import com.pingpongx.smb.fee.api.dtos.cmd.common.PayeeInfo;
import com.pingpongx.smb.fee.api.dtos.cmd.common.PayerInfo;
import com.pingpongx.smb.fee.api.dtos.cmd.common.RateInfo;
import com.pingpongx.smb.fee.api.dtos.cmd.trade.BillingRequest;
import com.pingpongx.smb.fee.api.dtos.cmd.trade.OrderInfo;
import com.pingpongx.smb.fee.api.enums.FeePayer;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;


public class AbstractFeeTestDependency extends MockedTest {
    public BillingRequest generateBillingRequest() {
        BillingRequest request = new BillingRequest();
        request.setBillingTime(System.currentTimeMillis());

        request.setCouponList(new ArrayList<>());
        request.setBizLine("FM");
        request.setCostNodeCode("ClientTransferStart");
        request.setSourceApp("FMPayout");
        Map<String, RateInfo> currencyMap = new HashMap<>();
        currencyMap.put("CNY_USD", RateInfo.of("CNY","USD","0.7",123123L));
        request.setFxRate(currencyMap);

        OrderInfo orderInfo = new OrderInfo();
        orderInfo.setBizOrderId("TestBizOrderId");
        orderInfo.setBizOrderType("B2B");

        PayeeInfo payeeInfo = new PayeeInfo();
        payeeInfo.setPayeeAccountNo("X1000101010100101");
        payeeInfo.setPayeeName("被付款方名");
        payeeInfo.setBankName("硅谷破产银行");
        payeeInfo.setClientIdType("DID");
        payeeInfo.setClientId("D1230912380912830");
        orderInfo.setPayeeInfo(payeeInfo);
        PayerInfo payerInfo = new PayerInfo();
        payerInfo.setClientId("D12038901283102938");
        payerInfo.setClientIdType("DID");
        payerInfo.setPayerName("付款方名");
        payerInfo.setCertificateNumber("大壮");
        payerInfo.setReservedPhone("15015011501");
        orderInfo.setPayerInfo(payerInfo);

        orderInfo.setAmount(new BigDecimal("11111"));
        orderInfo.setSourceCurrency("USD");
        orderInfo.setTargetCurrency("CNY");
        orderInfo.setSubject("D12038901283102938");
        orderInfo.setSubjectType("DID");
        orderInfo.setFeePayer(FeePayer.OrderPayer.name());
        orderInfo.setBizOrderType("Transfer");
        orderInfo.setBizOrderId("T0192091203121");
        request.setOrderInfo(orderInfo);
        request.setSubject(orderInfo.getSubject());
        request.setSubjectType(orderInfo.getSubjectType());
        String jsonStr = JSON.toJSONString(request);
        BillingRequest parsed = JSON.parseObject(jsonStr, BillingRequest.class);
//{"billingTime":1678930994261,"bizLine":"FM","costNodeCode":"ClientTransferStart","couponList":[],"fxRate":{"CNY_USD":0.6999999999999999555910790149937383830547332763671875},"fxRateId":"FX213123123","orderInfo":{"amount":11111,"bizOrderId":"T0192091203121","bizOrderType":"Transfer","feePayer":"Payee","payeeInfo":{"bankName":"硅谷破产银行","clientId":"D1230912380912830","clientIdType":"DID","payeeAccountNo":"X1000101010100101","payeeName":"被付款方名"},"payerInfo":{"certificateNumber":"大壮","clientId":"D12038901283102938","clientIdType":"DID","payerName":"付款方名","reservedPhone":"15015011501"},"sourceCurrency":"USD","subject":"D12038901283102938","subjectType":"DID","targetCurrency":"CNY"},"sourceApp":"FMPayout","subject":"D12038901283102938","subjectType":"DID"}
        return request;
    }
}
