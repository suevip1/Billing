package com.pingpongx.smb.fee.server.service.impl;

import com.pingpongx.flowmore.cloud.base.server.app.BaseApplicationRun;
import com.pingpongx.smb.fee.common.dto.FeeConfigDTO;
import com.pingpongx.smb.fee.server.SmbFeeApplication;
import com.pingpongx.smb.fee.server.service.FeeConfigService;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.Assert.*;

/**
 * @author wangcheng
 * @Description TODO
 * @createTime 2022年07月02日 09:50:00
 */
@Slf4j
@SpringBootTest(classes = {SmbFeeApplication.class, BaseApplicationRun.class})
public class FeeConfigServiceImplTest {

    @Autowired
    private FeeConfigService feeConfigService;

    @Test
    public void addFeeConfig() {

        FeeConfigDTO dto = new FeeConfigDTO();
        dto.setBu("FLOWMORE");
        dto.setScope("1111");
        dto.setOriginCurrency("ALL");
        dto.setTargetCurrency("ALL");
        dto.setOrderType("");

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
    }
}