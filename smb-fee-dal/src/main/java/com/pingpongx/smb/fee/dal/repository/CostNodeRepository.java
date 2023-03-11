package com.pingpongx.smb.fee.dal.repository;

import com.pingpongx.smb.fee.dal.dataobject.CostItemDo;
import com.pingpongx.smb.fee.dal.dataobject.CostNodeDo;
import com.pingpongx.smb.fee.dal.mapper.CostItemMapper;
import com.pingpongx.smb.fee.dal.mapper.CostNodeMapper;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author xuyq
 * @createTime 2022年06月28日 14:47:00
 */
@Repository
public class CostNodeRepository extends FeeBaseRepository<CostNodeMapper, CostNodeDo> {

    public List<CostNodeDo> listAllNode(){
        return this.lambdaQuery().list();
    }

}
