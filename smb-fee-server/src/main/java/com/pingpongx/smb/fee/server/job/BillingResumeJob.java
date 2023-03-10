package com.pingpongx.smb.fee.server.job;

import com.alibaba.fastjson.JSONObject;
import com.pingpongx.job.core.biz.model.ReturnT;
import com.pingpongx.job.core.handler.IJobHandler;
import com.pingpongx.job.core.handler.annotation.JobHandler;
import com.pingpongx.smb.fee.common.dto.FeeConfigDTO;
import com.pingpongx.smb.fee.dal.dataobject.BillingContextDo;
import com.pingpongx.smb.fee.dal.repository.BillingContextRepository;
import com.pingpongx.smb.fee.dependency.convert.BillingContextConvert;
import com.pingpongx.smb.fee.domain.runtime.BillingContext;
import com.pingpongx.smb.fee.server.service.FeeConfigService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * @author xuyq
 * @createTime 2022年07月04日 11:18:00
 */
@Slf4j
@Component
@JobHandler("BillingResumeJob")
@RequiredArgsConstructor
public class BillingResumeJob extends IJobHandler {
    private final BillingContextRepository contextRepository;
    private final ApplicationContext applicationContext;

    @Override
    public ReturnT<String> execute(String repeatKey) throws Exception {
        if (repeatKey!=null){
            BillingContextDo dbo = contextRepository.findByRepeatKey(repeatKey);
            resumeOne(dbo);
            return ReturnT.SUCCESS;
        }
        List<BillingContextDo> list = contextRepository.findForResume();
        list.stream().forEach(this::resumeOne);
        return ReturnT.SUCCESS;
    }

    private void resumeOne(BillingContextDo dbo){
        BillingContext context = BillingContextConvert.toContext(dbo);
        applicationContext.publishEvent(context.resume(null));
    }
}
