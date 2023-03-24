package com.pingpongx.smb.fee.api.feign;

import com.pingpongx.smb.fee.api.dtos.cmd.BillingRequest;
import com.pingpongx.smb.fee.api.dtos.resp.Bill;
import com.pingpongx.smb.fee.common.constants.FeeConstants;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;


/**
 * @author wangcheng
 * @Description TODO
 * @createTime 2022年07月02日 10:14:00
 */
@FeignClient(value = FeeConstants.APP_NAME, path = BillingConfigServiceFeign.BASE_PATH)
public interface BillingConfigServiceFeign {

    String BASE_PATH = FeeConstants.RPC_PATH_V1 + "/smb-fee";

    /**
     * 获取费率
     * @param request
     * @return
     */
    @GetMapping("/refresh")
    Bill refresh();


    @PostMapping("/billing")
    Bill billing(@RequestBody BillingRequest request);

}
