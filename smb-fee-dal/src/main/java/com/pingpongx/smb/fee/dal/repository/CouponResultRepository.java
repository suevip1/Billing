package com.pingpongx.smb.fee.dal.repository;

import com.pingpongx.smb.fee.dal.dataobject.BillingRequestDo;
import com.pingpongx.smb.fee.dal.dataobject.CouponResultDo;
import com.pingpongx.smb.fee.dal.mapper.BillingRequestMapper;
import com.pingpongx.smb.fee.dal.mapper.CouponResultMapper;
import org.springframework.stereotype.Repository;

/**
 * @author wangcheng
 * @Description TODO
 * @createTime 2022年06月28日 14:47:00
 */
@Repository
public class CouponResultRepository extends FeeBaseRepository<CouponResultMapper, CouponResultDo> {

    public static final String ALL = "ALL";

}
