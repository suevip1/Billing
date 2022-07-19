package com.pingpongx.smb.fee.dal.repository;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.pingpongx.business.dal.core.BaseRepository;
import com.pingpongx.smb.fee.common.dto.OrderInfoDTO;
import com.pingpongx.smb.fee.common.req.FeeConfigRequest;
import com.pingpongx.smb.fee.dal.dataobject.FeeConfig;
import com.pingpongx.smb.fee.dal.mapper.FeeConfigMapper;
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
public class FeeConfigRepository extends BaseRepository<FeeConfigMapper, FeeConfig> {

    public static final String ALL = "ALL";

    public IPage<FeeConfig> getFeeConfigPage(FeeConfigRequest request) {
        IPage<FeeConfig> page = new Page<>(request.getPageNo(), request.getPageNo());
        QueryWrapper<FeeConfig> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq(!StringUtils.isEmpty(request.getBu()), "bu", request.getBu())
                .eq(!StringUtils.isEmpty(request.getBusiness()), "business", request.getBusiness())
                .eq(Objects.nonNull(request.getConfigType()), "configType", request.getConfigType())
                .orderByDesc("modified");
        return this.page(page, queryWrapper);
    }

    public List<FeeConfig> getFeeConfigList(OrderInfoDTO dto) {
        return this.list(new QueryWrapper<FeeConfig>()
                .eq("bu", dto.getBu())
                .eq("orderType", dto.getOrderType())
                .in("scope", Arrays.asList(ALL, dto.getClientId())));
    }

}
