package com.pingpongx.smb.fee.server.service;

import com.pingpongx.business.common.dto.OrderDTO;
import com.pingpongx.smb.fee.common.dto.FeeConfigDTO;
import com.pingpongx.smb.fee.common.dto.OrderInfoDTO;
import com.pingpongx.smb.fee.common.dto.PageDTO;
import com.pingpongx.smb.fee.common.req.FeeConfigRequest;
import com.pingpongx.smb.fee.common.resp.FeeConfigResponse;

/**
 * @author wangcheng
 * @Description TODO
 * @createTime 2022年06月28日 14:56:00
 */
public interface FeeConfigService {

    /**
     * 新增费率配置
     * @param dto
     */
    void addFeeConfig(FeeConfigDTO dto);

    /**
     * 更新费率配置
     * @param dto
     */
    void updateFeeConfig(FeeConfigDTO dto);

    /**
     * 删除费率配置
     * @param id
     */
    void deleteFeeConfig(Integer id);

    /**
     * 分页查询费率配置
     * @return
     */
    PageDTO<FeeConfigDTO> getFeeConfigPage(FeeConfigRequest request);

    /**
     * 获取费率信息
     * @param dto
     * @return
     */
    FeeConfigResponse getOrderFee(OrderInfoDTO dto);




}
