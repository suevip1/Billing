package com.pingpongx.smb.fee.domain.runtime.StageHandler;

import com.pingpongx.smb.fee.dal.dataobject.BillingContextDo;
import com.pingpongx.smb.fee.dal.repository.BillingContextRepository;
import com.pingpongx.smb.fee.domain.convert.runtime.BillingContextConvert;
import com.pingpongx.smb.fee.domain.module.event.BillingRequestReceived;
import com.pingpongx.smb.fee.domain.module.event.BillingStage;
import com.pingpongx.smb.fee.domain.module.event.MatchParamCompleted;
import com.pingpongx.smb.fee.domain.runtime.BillingContext;
import lombok.RequiredArgsConstructor;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Service;

/*****
 * 所有时间抛出后的 context 更新落库，设计要求计费系统无外部依赖，所有向外部调用的rpc 转化为明确的入参。
 * 做到函数编程的基本要求。入参相同，返回的结果一定相同。为本地复现线上问题提供支撑。应对计费逻辑黑盒问题。
 * 以上大前提满足情况下，context落库在补偿鉴权的情况下可以不落在同一事务，线上复杂问题可以转化为本地debug单步调试。
 *
 * 无状态，可复现，无外部依赖设计原则不可被腐化，不然这里的不在事务下的持久化逻辑会出问题。
 */
@Service
@RequiredArgsConstructor
public class BillingContextAsyncPersistence {
    private final BillingContextRepository repository;

    @EventListener
    void persistenceRequestReceived(BillingRequestReceived stage) {
        doUpdate(stage.getContext());
    }

    @EventListener
    void persistenceRequestReceived(MatchParamCompleted stage) {
        doUpdate(stage.getContext());
    }

    @EventListener
    void persistenceRequestReceived(BillingStage stage) {
        doUpdate(stage.getContext());
    }

    private void doUpdate(BillingContext context){
        BillingContextDo dos = BillingContextConvert.toDo(context);
        repository.saveOrUpdate(dos);
    }
}
