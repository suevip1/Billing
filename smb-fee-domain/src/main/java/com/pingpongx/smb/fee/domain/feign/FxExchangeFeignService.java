package com.pingpongx.smb.fee.domain.feign;

import com.pingpongx.smb.fee.common.constants.FeeConstants;
import com.pingpongx.smb.fee.common.resp.FinalExchangeRateResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;


/**
 * @author wangcheng
 * @Description TODO
 * @createTime 2022年07月05日 11:38:00
 */
@FeignClient(value = FeeConstants.APP_NAME, path = FeeConstants.RPC_PATH_V1)
public interface FxExchangeFeignService {

    String BASE_PATH = FeeConstants.RPC_PATH_V1;

    /**
     * 获取汇率列表
     * @return
     */
    @PostMapping("/get/amountEstimate/exchangeRate")
    FinalExchangeRateResponse getAmountEstimateExchangeRate();
}
