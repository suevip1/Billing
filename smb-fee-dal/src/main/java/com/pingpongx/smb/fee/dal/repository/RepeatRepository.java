package com.pingpongx.smb.fee.dal.repository;

import com.pingpongx.smb.fee.dal.dataobject.RepeatDo;
import com.pingpongx.smb.fee.dal.mapper.RepeatMapper;
import org.springframework.stereotype.Repository;

/**
 * @author xuyq
 * @createTime 2022年06月28日 14:47:00
 */
@Repository
public class RepeatRepository extends FeeBaseRepository<RepeatMapper, RepeatDo> {

    public static final String ALL = "ALL";


}
