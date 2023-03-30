package com.pingpongx.smb.fee.api.feign;

import com.pingpongx.smb.fee.api.dtos.cmd.BillingRequest;
import com.pingpongx.smb.fee.api.dtos.resp.Bill;
import com.pingpongx.smb.fee.api.dtos.resp.CostItemResult;
import com.pingpongx.smb.fee.common.constants.FeeConstants;
import com.pingpongx.smb.metadata.dto.VariableDefDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;


/**
 * @author wangcheng
 * @Description TODO
 * @createTime 2022年07月02日 10:14:00
 */
@FeignClient(value = FeeConstants.APP_NAME, path = BillingConfigServiceFeign.BASE_PATH)
public interface BillingConfigServiceFeign {

    String BASE_PATH = FeeConstants.RPC_PATH_V1 + "/smb-fee-conf";

    /**
     * 获取费率
     * @param request
     * @return
     */
    @GetMapping("/refresh")
    void refresh(@RequestParam String costNode);


    @PostMapping("/variable/saveOrUpdate")
    Bill saveOrUpdateVariable(@RequestBody VariableDefDto request);

}
