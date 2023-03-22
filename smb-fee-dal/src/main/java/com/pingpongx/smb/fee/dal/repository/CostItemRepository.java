package com.pingpongx.smb.fee.dal.repository;

import com.baomidou.mybatisplus.core.conditions.AbstractWrapper;
import com.pingpongx.smb.fee.dal.dataobject.CostItemDo;
import com.pingpongx.smb.fee.dal.dataobject.CostItemResultDo;
import com.pingpongx.smb.fee.dal.mapper.CostItemMapper;
import com.pingpongx.smb.fee.dal.mapper.CostItemResultMapper;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

/**
 * @author xuyq
 * @createTime 2022年06月28日 14:47:00
 */
@Repository
public class CostItemRepository extends FeeBaseRepository<CostItemMapper, CostItemDo> {

    public List<CostItemDo> listByNodeCode(String costNodeCode){
        AbstractWrapper wrapper = this.lambdaQuery().eq(CostItemDo::getCostNodeCode,costNodeCode).getWrapper();
        return this.list(wrapper);
    }
    public List<CostItemDo> listByCodeList(List<String> codeList){
        if (codeList==null||codeList.isEmpty()){
            return new ArrayList<>();
        }
        AbstractWrapper wrapper = this.lambdaQuery().in(CostItemDo::getCode,codeList).getWrapper();
        return this.list(wrapper);
    }
}
