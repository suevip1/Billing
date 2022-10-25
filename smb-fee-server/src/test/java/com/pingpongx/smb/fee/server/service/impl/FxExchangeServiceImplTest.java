package com.pingpongx.smb.fee.server.service.impl;

import com.alibaba.fastjson.JSONObject;
import com.pingpongx.flowmore.cloud.base.server.app.BaseApplicationRun;
import com.pingpongx.flowmore.cloud.base.server.app.BaseTest;
import com.pingpongx.smb.fee.api.feign.FxExchangeFeignService;
import com.pingpongx.smb.fee.common.resp.FinalExchangeRateResponse;
import com.pingpongx.smb.fee.server.SmbFeeApplication;
import com.pingpongx.smb.fee.server.service.FxExchangeService;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import static org.junit.Assert.*;

/**
 * @author wangcheng
 * @Description TODO
 * @createTime 2022年07月05日 11:46:00
 */
@Slf4j
@SpringBootTest(classes = {SmbFeeApplication.class, BaseApplicationRun.class})
@RunWith(SpringJUnit4ClassRunner.class)
public class FxExchangeServiceImplTest extends BaseTest {

    @Autowired
    private FxExchangeService fxExchangeService;

    @Autowired
    private FxExchangeFeignService fxExchangeFeignService;

    @Test
    public void getAmountEstimateExchangeRate() {
        fxExchangeService.getAmountEstimateExchangeRate();
    }

    @Test
    public void test2() {
        FinalExchangeRateResponse amountEstimateExchangeRate = fxExchangeFeignService.getAmountEstimateExchangeRate();
        System.out.println(JSONObject.toJSONString(amountEstimateExchangeRate));
    }
}