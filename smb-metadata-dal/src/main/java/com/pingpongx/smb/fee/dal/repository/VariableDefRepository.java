package com.pingpongx.smb.fee.dal.repository;

import com.baomidou.mybatisplus.extension.service.additional.query.impl.LambdaQueryChainWrapper;
import com.pingpongx.business.dal.core.BaseRepository;
import com.pingpongx.smb.fee.dal.dataobject.ConfiguredVariableDef;
import com.pingpongx.smb.fee.dal.dataobject.MetaBaseDo;
import com.pingpongx.smb.fee.dal.mapper.VariableDefMapper;
import com.pingpongx.smb.metadata.qry.GetByCode;
import com.pingpongx.smb.metadata.qry.ListAll;
import org.springframework.stereotype.Repository;
import org.springframework.util.StringUtils;

import java.util.Collection;
import java.util.Collections;
import java.util.List;

/**
 * @author xuyq
 * @createTime 2022年06月28日 14:47:00
 */
@Repository
public class VariableDefRepository extends MetaBaseRepository<VariableDefMapper, ConfiguredVariableDef> {

    public static final String ALL = "ALL";

    public List<ConfiguredVariableDef> getByCodeAndNameSpace(Collection<String> req,List<String> namespaces) {
        LambdaQueryChainWrapper queryChainWrapper = this.lambdaQuery().in(ConfiguredVariableDef::getCode, req).in(ConfiguredVariableDef::getNameSpace, namespaces);
        return this.list(queryChainWrapper.getWrapper());
    }
    public List<ConfiguredVariableDef> getByCode(Collection<String> req) {
        LambdaQueryChainWrapper queryChainWrapper = this.lambdaQuery().in(ConfiguredVariableDef::getCode, req);
        return this.list(queryChainWrapper.getWrapper());
    }

    public ConfiguredVariableDef getByCode(String code) {
        LambdaQueryChainWrapper queryChainWrapper = this.lambdaQuery()
                .eq(ConfiguredVariableDef::getCode, code);
        return this.getOne(queryChainWrapper.getWrapper());
    }

    public List<ConfiguredVariableDef> listAll(String nameSpace) {
        LambdaQueryChainWrapper queryChainWrapper = this.lambdaQuery().eq(StringUtils.isEmpty(nameSpace), ConfiguredVariableDef::getNameSpace, nameSpace);
        return this.list(queryChainWrapper.getWrapper());
    }

}
