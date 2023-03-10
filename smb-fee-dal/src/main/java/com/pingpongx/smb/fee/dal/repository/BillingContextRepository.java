package com.pingpongx.smb.fee.dal.repository;

import com.pingpongx.smb.fee.dal.dataobject.BillingContextDo;
import com.pingpongx.smb.fee.dal.dataobject.BillingRequestDo;
import com.pingpongx.smb.fee.dal.dataobject.RepeatDo;
import com.pingpongx.smb.fee.dal.mapper.BillingContextMapper;
import com.pingpongx.smb.fee.dal.mapper.RepeatMapper;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author xuyq
 * @createTime 2022年06月28日 14:47:00
 */
@Repository
public class BillingContextRepository extends FeeBaseRepository<BillingContextMapper, BillingContextDo> {

    public static final String ALL = "ALL";
    public BillingContextDo findByRepeatKey(String repeatKey){
        return this.getOne(this.lambdaQuery().eq(BillingContextDo::getRequestRepeatKey,repeatKey).getWrapper());
    }
    public List<BillingContextDo> findForResume(){
        return this.list(this.lambdaQuery().isNull(BillingContextDo::getBill).getWrapper());
    }

}
