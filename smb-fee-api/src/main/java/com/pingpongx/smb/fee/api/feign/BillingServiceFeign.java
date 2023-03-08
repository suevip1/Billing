package com.pingpongx.smb.fee.api.feign;

import com.pingpongx.smb.fee.api.dtos.BillingDetail;
import com.pingpongx.smb.fee.api.dtos.cmd.BillingRequest;
import com.pingpongx.smb.fee.common.constants.FeeConstants;
import com.pingpongx.smb.fee.common.dto.OrderInfoDTO;
import com.pingpongx.smb.fee.common.resp.FeeConfigResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import static com.pingpongx.smb.fee.common.constants.FeeConstants.BASE_PATH;


/**
 * @author wangcheng
 * @Description TODO
 * @createTime 2022年07月02日 10:14:00
 */
@FeignClient(value = FeeConstants.APP_NAME, path = BillingServiceFeign.BASE_PATH)
public interface BillingServiceFeign {

    String BASE_PATH = FeeConstants.RPC_PATH_V1 + "/smb-fee";

    /**
     * 获取费率
     * @param request
     * @return
     */
    @PostMapping("/trial")
    BillingDetail trial(@RequestBody BillingRequest request);


    @PostMapping("/billing")
    BillingDetail billing(@RequestBody BillingRequest request);

}
