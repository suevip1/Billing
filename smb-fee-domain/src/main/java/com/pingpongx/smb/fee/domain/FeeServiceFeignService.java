package com.pingpongx.smb.fee.domain;

import com.pingpongx.smb.fee.common.constants.FeeConstants;
import com.pingpongx.smb.fee.common.dto.OrderInfoDTO;
import com.pingpongx.smb.fee.common.resp.FeeConfigResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;


/**
 * @author wangcheng
 * @Description TODO
 * @createTime 2022年07月02日 10:14:00
 */
@FeignClient(value = FeeConstants.APP_NAME, path = com.pingpongx.smb.fee.domain.FeeServiceFeignService.BASE_PATH)
public interface FeeServiceFeignService {

    String BASE_PATH = FeeConstants.RPC_PATH_V1 + "/smb-fee";


    /**
     * 获取费率
     * @param request
     * @return
     */
    @PostMapping("/get/orderFee")
    FeeConfigResponse getOrderFee(@RequestBody OrderInfoDTO request);

}
