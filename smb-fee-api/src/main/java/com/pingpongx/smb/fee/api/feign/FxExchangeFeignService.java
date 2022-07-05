package com.pingpongx.smb.fee.api.feign;

import com.pingpongx.smb.fee.common.constants.FeeConstants;
import com.pingpongx.smb.fee.common.resp.ExchangeRateResponse;
import com.pingpongx.smb.fee.common.resp.FeeConfigResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

/**
 * @author wangcheng
 * @Description TODO
 * @createTime 2022年07月05日 11:38:00
 */
@FeignClient(value = FeeConstants.APP_NAME, path = FxExchangeFeignService.BASE_PATH)
public interface FxExchangeFeignService {

    String BASE_PATH = FeeConstants.RPC_PATH_V1 + "/smb-fee";

    /**
     * 获取汇率列表
     * @return
     */
    @PostMapping("/get/amountEstimate/exchangeRate")
    List<ExchangeRateResponse> getAmountEstimateExchangeRate();
}
