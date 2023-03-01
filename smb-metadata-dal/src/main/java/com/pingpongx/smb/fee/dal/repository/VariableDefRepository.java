package com.pingpongx.smb.fee.dal.repository;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.pingpongx.business.dal.core.BaseRepository;
import com.pingpongx.smb.fee.common.dto.OrderInfoDTO;
import com.pingpongx.smb.fee.common.req.FeeConfigRequest;
import com.pingpongx.smb.fee.dal.dataobject.ConfiguredVariableDef;
import com.pingpongx.smb.fee.dal.mapper.VariableDefMapper;
import org.springframework.stereotype.Repository;
import org.springframework.util.StringUtils;

import java.util.Arrays;
import java.util.List;
import java.util.Objects;

/**
 * @author wangcheng
 * @Description TODO
 * @createTime 2022年06月28日 14:47:00
 */
@Repository
public class VariableDefRepository extends BaseRepository<VariableDefMapper, ConfiguredVariableDef> {

    public static final String ALL = "ALL";

}
