package com.pingpongx.smb.fee.dal.repository;

import com.pingpongx.smb.fee.dal.dataobject.BillingRequestDo;
import com.pingpongx.smb.fee.dal.mapper.BillingRequestMapper;
import org.springframework.stereotype.Repository;

/**
 * @author wangcheng
 * @Description TODO
 * @createTime 2022年06月28日 14:47:00
 */
@Repository
public class BillingRequestRepository extends FeeBaseRepository<BillingRequestMapper, BillingRequestDo> {

    public static final String ALL = "ALL";

    public BillingRequestDo findByRepeatKey(String repeatKey){
        return this.getOne(this.lambdaQuery().eq(BillingRequestDo::getRepeatKey,repeatKey).getWrapper());
    }
}
