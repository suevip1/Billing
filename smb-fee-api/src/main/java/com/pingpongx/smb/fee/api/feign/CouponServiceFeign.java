package com.pingpongx.smb.fee.api.feign;

import com.pingpongx.smb.fee.api.dtos.cmd.coupon.BatchCmd;
import com.pingpongx.smb.fee.api.dtos.cmd.coupon.CouponRequest;
import com.pingpongx.smb.fee.api.dtos.resp.Bill;
import com.pingpongx.smb.fee.api.dtos.resp.BillList;
import com.pingpongx.smb.fee.common.constants.FeeConstants;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;


/**
 * @author wangcheng
 * @Description TODO
 * @createTime 2022年07月02日 10:14:00
 */
@FeignClient(value = FeeConstants.APP_NAME, path = CouponServiceFeign.BASE_PATH)
public interface CouponServiceFeign {

    String BASE_PATH = FeeConstants.RPC_PATH_V1 + "/smb-fee/coupon";

    /**
     * 获取费率
     * @param request
     * @return
     */
    @PostMapping("/trial")
    Bill trial(@RequestBody CouponRequest request);


    @PostMapping("/billing")
    Bill billing(@RequestBody CouponRequest request);

    @PostMapping("/batch/trial")
    BillList batchTrial(@RequestBody BatchCmd request);


    @PostMapping("/batch/billing")
    BillList batchBilling(@RequestBody BatchCmd request);

}
