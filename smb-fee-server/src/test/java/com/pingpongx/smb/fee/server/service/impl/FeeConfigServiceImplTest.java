package com.pingpongx.smb.fee.server.service.impl;

import com.pingpongx.business.common.dto.Money;
import com.pingpongx.flowmore.cloud.base.server.app.BaseApplicationRun;
import com.pingpongx.flowmore.cloud.base.server.app.BaseTest;
import com.pingpongx.smb.fee.common.dto.FeeConfigDTO;
import com.pingpongx.smb.fee.common.dto.OrderInfoDTO;
import com.pingpongx.smb.fee.server.SmbFeeApplication;
import com.pingpongx.smb.fee.server.service.FeeConfigService;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.math.BigDecimal;

import static org.junit.Assert.*;

/**
 * @author wangcheng
 * @Description TODO
 * @createTime 2022年07月02日 09:50:00
 */
@Slf4j
@SpringBootTest(classes = {SmbFeeApplication.class, BaseApplicationRun.class})
@RunWith(SpringJUnit4ClassRunner.class)
public class FeeConfigServiceImplTest extends BaseTest {

    @Autowired
    private FeeConfigService feeConfigService;

    @Test
    public void addFeeConfig() {

        FeeConfigDTO dto = new FeeConfigDTO();
        dto.setBu("FLOWMORE");
        dto.setScope("ALL");
        dto.setConfigType(0);
        dto.setOriginCurrency("USD");
        dto.setTargetCurrency("CNY");
        dto.setOrderType("WITHDRAW");
        dto.setFeeRate(new BigDecimal("0.01"));
        dto.setCutFeeRate(new BigDecimal("0.01"));
        dto.setFixFee(Money.builder().amount(new BigDecimal("3")).currency("CNY").build());
        dto.setExtraInfo("orangeTest");


        feeConfigService.addFeeConfig(dto);
    }

    @Test
    public void updateFeeConfig() {
    }

    @Test
    public void deleteFeeConfig() {
    }

    @Test
    public void getFeeConfigPage() {
    }

    @Test
    public void getOrderFee() {

        OrderInfoDTO orderInfoDTO = new OrderInfoDTO();
        orderInfoDTO.setBu("FLOWMORE");
        orderInfoDTO.setClientId("1111");
        orderInfoDTO.setOriginMoney(Money.builder().amount(new BigDecimal("20000")).currency("CNY").build());
        orderInfoDTO.setTargetMoney(Money.builder().currency("CNY").build());
        orderInfoDTO.setOrderType("WITHDRAW");

        feeConfigService.getOrderFee(orderInfoDTO);

    }
}