package com.pingpongx.smb.fee.server.job;

import com.alibaba.fastjson2.JSONObject;
import com.pingpongx.job.core.biz.model.ReturnT;
import com.pingpongx.job.core.handler.IJobHandler;
import com.pingpongx.job.core.handler.annotation.JobHandler;
import com.pingpongx.smb.fee.common.dto.FeeConfigDTO;
import com.pingpongx.smb.fee.server.service.FeeConfigService;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * @author wangcheng
 * @Description TODO
 * @createTime 2022年07月04日 11:18:00
 */
@Slf4j
@Component
@JobHandler("FeeConfigAddJob")
public class FeeConfigAddJob extends IJobHandler {

    @Autowired
    private FeeConfigService feeConfigService;

    @Override
    public ReturnT<String> execute(String s) throws Exception {
        if(StringUtils.isBlank(s)){
            return ReturnT.SUCCESS;
        }
        FeeConfigDTO feeConfigDTO = JSONObject.parseObject(s, FeeConfigDTO.class);
        feeConfigService.addFeeConfig(feeConfigDTO);
        return ReturnT.SUCCESS;
    }
}
