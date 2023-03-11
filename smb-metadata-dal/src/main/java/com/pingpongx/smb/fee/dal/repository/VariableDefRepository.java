package com.pingpongx.smb.fee.dal.repository;

import com.baomidou.mybatisplus.extension.service.additional.query.impl.LambdaQueryChainWrapper;
import com.pingpongx.business.dal.core.BaseRepository;
import com.pingpongx.smb.fee.dal.dataobject.ConfiguredVariableDef;
import com.pingpongx.smb.fee.dal.mapper.VariableDefMapper;
import com.pingpongx.smb.metadata.qry.GetByCode;
import com.pingpongx.smb.metadata.qry.ListAll;
import org.springframework.stereotype.Repository;
import org.springframework.util.StringUtils;

import java.util.List;

/**
 * @author xuyq
 * @createTime 2022年06月28日 14:47:00
 */
@Repository
public class VariableDefRepository extends BaseRepository<VariableDefMapper, ConfiguredVariableDef> {

    public static final String ALL = "ALL";

    public List<ConfiguredVariableDef> getByCode(GetByCode req) {
        LambdaQueryChainWrapper queryChainWrapper = this.lambdaQuery().eq(StringUtils.isEmpty(req.getNamespace()), ConfiguredVariableDef::getNameSpace, req.getNamespace()).in(ConfiguredVariableDef::getCode, req.getDefCode());
        return this.list(queryChainWrapper.getWrapper());
    }

    public ConfiguredVariableDef getByCode(String code) {
        LambdaQueryChainWrapper queryChainWrapper = this.lambdaQuery()
                .eq(ConfiguredVariableDef::getCode, code);
        return this.getOne(queryChainWrapper.getWrapper());
    }

    public List<ConfiguredVariableDef> listAll(ListAll req) {
        LambdaQueryChainWrapper queryChainWrapper = this.lambdaQuery().eq(StringUtils.isEmpty(req.getNamespace()), ConfiguredVariableDef::getNameSpace, req.getNamespace());
        return this.list(queryChainWrapper.getWrapper());
    }

}
