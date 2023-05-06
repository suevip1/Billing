package com.pingpongx.smb.fee.domain.service;

import com.alibaba.fastjson2.JSON;
import com.alibaba.fastjson2.JSONWriter;
import com.pingpongx.smb.export.RuleConstant;
import com.pingpongx.smb.export.module.persistance.*;
import com.pingpongx.smb.fee.MockedTest;
import com.pingpongx.smb.fee.api.dtos.cmd.common.PayeeInfo;
import com.pingpongx.smb.fee.api.dtos.cmd.common.PayerInfo;
import com.pingpongx.smb.fee.api.dtos.cmd.common.RateInfo;
import com.pingpongx.smb.fee.api.dtos.cmd.trade.BillingRequest;
import com.pingpongx.smb.fee.api.dtos.cmd.trade.OrderInfo;
import com.pingpongx.smb.fee.api.dtos.expr.*;
import com.pingpongx.smb.fee.api.enums.FeePayer;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;


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
        orderInfo.setFeePayer(FeePayer.OrderPayee.name());
        orderInfo.setBizOrderType("Transfer");
        orderInfo.setBizOrderId("T0192091203121");
        orderInfo.setExtraInfo(new HashMap<>());
        orderInfo.getExtraInfo().put("isUrgent", "true");
        request.setOrderInfo(orderInfo);
        request.setSubject(orderInfo.getSubject());
        request.setSubjectType(orderInfo.getSubjectType());
        String jsonStr = JSON.toJSONString(request);
        BillingRequest parsed = JSON.parseObject(jsonStr, BillingRequest.class);
//{"billingTime":1678930994261,"bizLine":"FM","costNodeCode":"ClientTransferStart","couponList":[],"fxRate":{"CNY_USD":0.6999999999999999555910790149937383830547332763671875},"fxRateId":"FX213123123","orderInfo":{"amount":11111,"bizOrderId":"T0192091203121","bizOrderType":"Transfer","feePayer":"Payee","payeeInfo":{"bankName":"硅谷破产银行","clientId":"D1230912380912830","clientIdType":"DID","payeeAccountNo":"X1000101010100101","payeeName":"被付款方名"},"payerInfo":{"certificateNumber":"大壮","clientId":"D12038901283102938","clientIdType":"DID","payerName":"付款方名","reservedPhone":"15015011501"},"sourceCurrency":"USD","subject":"D12038901283102938","subjectType":"DID","targetCurrency":"CNY"},"sourceApp":"FMPayout","subject":"D12038901283102938","subjectType":"DID"}
        return request;
    }

    FixDto generateFixDto() {
        FixDto fixDto = new FixDto();
        fixDto.setFix("1");
        return fixDto;
    }

    AXpBDto generateAXpBDto() {
        AXpBDto aXpBDto = new AXpBDto();
        aXpBDto.setA("0.01");
        aXpBDto.setB("0");
        aXpBDto.setVarCode("amount");
        return aXpBDto;
    }

    MaxDto generateMaxDto() {
        MaxDto dto = new MaxDto();
        dto.setList(Stream.of(generateAXpBDto(),generateFixDto()).collect(Collectors.toList()));
        return dto;
    }

    MinDto generateMinDto() {
        MinDto dto = new MinDto();
        dto.setList(Stream.of(generateAXpBDto(),generateFixDto()).collect(Collectors.toList()));
        return dto;
    }

    TierDto generateTierDto() {
        TierDto tierDto = new TierDto();
        tierDto.setVarCode("amount");

        NodeWithContidionDto node1 = new NodeWithContidionDto();
        node1.setExpr(generateFixDto());
        Range r1 = new Range();
        r1.setRangeType(RangeType.LCRO.name());
        r1.setRangeStart("0");
        r1.setRangeEnd("5000");
        node1.setCondition(r1);

        NodeWithContidionDto node2 = new NodeWithContidionDto();
        node2.setExpr(generateAXpBDto());
        Range r2 = new Range();
        r2.setRangeType(RangeType.LCRC.name());
        r2.setRangeStart("5000");
        r2.setRangeEnd(Range.MAX.toString());
        node2.setCondition(r2);
        tierDto.setList(Stream.of(node1,node2).collect(Collectors.toList()));
        return tierDto;
    }

    RuleDto generateMatchRule() {

        LeafRuleConf leafRuleConf = new LeafRuleConf();
        leafRuleConf.setAttr("isUrgent");
        leafRuleConf.setType("BillingContext");
        leafRuleConf.setNot(false);
        leafRuleConf.setOperation(RuleConstant.Operations.StrEquals.getSimpleName());
        leafRuleConf.setExpected("true");
        String test = JSON.toJSONString(leafRuleConf, JSONWriter.Feature.WriteClassName);
        And and = new And();
        List<RuleDto> list = new ArrayList<>();
        list.add(leafRuleConf);
        and.setAndRules(list);
        return and;
    }

    RuleDto matchRule() {

        LeafRuleConf leafRuleConf = new LeafRuleConf();
        leafRuleConf.setAttr("amount");
        leafRuleConf.setType("BillingContext");
        leafRuleConf.setNot(false);
        leafRuleConf.setOperation(RuleConstant.Operations.NumberRangeIn.getSimpleName());

        Range r1 = new Range();
        r1.setRangeType(RangeType.LCRO.name());
        r1.setRangeStart("0");
        r1.setRangeEnd("50");

        leafRuleConf.setExpected(JSON.toJSONString(r1));
        String test = JSON.toJSONString(leafRuleConf, JSONWriter.Feature.WriteClassName);
        JSON.parseObject(test, RuleDto.class);


        return leafRuleConf;
    }
}
