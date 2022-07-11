package com.pingpongx.smb.fee.server.service.impl;

import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.pingpongx.business.common.dto.Money;
import com.pingpongx.business.common.exception.Assert;
import com.pingpongx.smb.fee.common.constants.FeeConfigConstants;
import com.pingpongx.smb.fee.common.constants.FeeConfigTypeEnum;
import com.pingpongx.smb.fee.common.dto.FeeConfigDTO;
import com.pingpongx.smb.fee.common.dto.OrderInfoDTO;
import com.pingpongx.smb.fee.common.dto.PageDTO;
import com.pingpongx.smb.fee.common.req.FeeConfigRequest;
import com.pingpongx.smb.fee.common.resp.FeeConfigResponse;
import com.pingpongx.smb.fee.dal.dataobject.FeeConfig;
import com.pingpongx.smb.fee.dal.repository.FeeConfigRepository;
import com.pingpongx.smb.fee.server.service.FeeConfigService;
import com.pingpongx.smb.fee.server.utils.BeanUtils;
import com.pingpongx.smb.fee.server.utils.PageUtil;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.math.BigDecimal;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

/**
 * @author wangcheng
 * @Description TODO
 * @createTime 2022年06月28日 14:57:00
 */
@Slf4j
@Service
public class FeeConfigServiceImpl implements FeeConfigService {

    @Autowired
    private FeeConfigRepository feeConfigRepository;

    @Override
    public void addFeeConfig(FeeConfigDTO dto) {

        log.info("费率配置新增:{}", dto);
        Assert.warnIsTrue(dto.getFeeRate() != null && dto.getFeeRate().compareTo(BigDecimal.valueOf(0.1)) <= 0, "fee rate【" + dto.getFeeRate() + "】must not exceed 0.1");
        Assert.warnIsTrue(dto.getFeeRate().compareTo(BigDecimal.ZERO) >= 0, "fee rate【" + dto.getFeeRate() + "】must be equal or greater than 0");
        Assert.warnIsTrue(dto.getFixFee() == null || dto.getFixFee().getAmount().compareTo(BigDecimal.ZERO) >= 0, "fixFee【" + dto.getFixFee() + "】must be equal or greater than 0");
        Assert.warnIsTrue(dto.getMinPayout() == null || dto.getMinPayout().getAmount().compareTo(BigDecimal.ZERO) >= 0, "minPayout【" + dto.getMinPayout() + "】must be equal or greater than 0");
        Assert.warnIsTrue(dto.getLessThanMinPayoutFee() == null || dto.getLessThanMinPayoutFee().getAmount().compareTo(BigDecimal.ZERO) >= 0, "LessThanMinPayoutFee【" + dto.getLessThanMinPayoutFee() + "】must be equal or greater than 0");
        Assert.warnIsTrue(dto.getMaxFee() == null || dto.getMaxFee().getAmount().compareTo(BigDecimal.ZERO) >= 0, "MaxFee【" + dto.getMaxFee() + "】must be equal or greater than 0");

        FeeConfig feeConfig = BeanUtils.convert(dto, FeeConfig.class);
        FeeConfig findFeeConfig = feeConfigRepository.getOne(new QueryWrapper<FeeConfig>().eq("scope", dto.getScope()).eq("orderType", dto.getOrderType()).eq("originCurrency", dto.getOriginCurrency()).eq("targetCurrency", dto
                .getTargetCurrency()).orderByDesc("modified").last("limit 1"));
        if (Objects.nonNull(findFeeConfig)) {
            feeConfig.setId(findFeeConfig.getId());
            feeConfigRepository.updateById(feeConfig);
        } else {
            feeConfigRepository.save(feeConfig);
        }
    }

    @Override
    public void updateFeeConfig(FeeConfigDTO dto) {
        Assert.warnNotEmpty(dto.getId(), "id 不能为空");
        FeeConfig feeConfig = BeanUtils.convert(dto, FeeConfig.class);
        feeConfigRepository.updateById(feeConfig);
    }

    @Override
    public void deleteFeeConfig(Integer id) {
        feeConfigRepository.removeById(id);
    }

    @Override
    public PageDTO<FeeConfigDTO> getFeeConfigPage(FeeConfigRequest request) {
        IPage<FeeConfig> feeConfigPage = feeConfigRepository.getFeeConfigPage(request);
        List<FeeConfigDTO> list = feeConfigPage.getRecords().stream().map(db -> BeanUtils.convert(db, FeeConfigDTO.class)).collect(Collectors.toList());
        log.info("查询费率列表{}", list);
        return PageUtil.covertWithRecords(feeConfigPage, list);
    }

    @Override
    public FeeConfigResponse getOrderFee(OrderInfoDTO orderInfo) {
        log.info("开始请求计算订单费率:{}", orderInfo);
        // 1 参数验证
        checkOrderInfo(orderInfo);
        // 2 获取费率配置 考虑兜底？
        List<FeeConfig> feeConfigList = feeConfigRepository.getFeeConfigList(orderInfo);
        // 3 选择最佳配置
        FeeConfig bestMatchFeeConfig = getBestMatchFeeConfig(orderInfo, feeConfigList);
        Assert.errorNotEmpty(bestMatchFeeConfig, "没有找到匹配的费率配置," + orderInfo.getOrderId());
        // 4 计算费率
        FeeConfigResponse feeConfigResponse = calculateFeeInfo(orderInfo, bestMatchFeeConfig);
        log.info("最终计算费率:{}", feeConfigResponse);
        return feeConfigResponse;
    }


    /**
     * 费率前置订单校验
     *
     * @param dto
     */
    private void checkOrderInfo(OrderInfoDTO dto) {
        Assert.warnNotEmpty(dto.getClientId(), "用户id不能为空");
        Assert.warnNotEmpty(dto.getBu(), "bu不能为空");
        Assert.warnNotEmpty(dto.getOrderType(), "orderType不能为空");
        Assert.warnIsTrue(Objects.nonNull(dto.getMoney()) && StringUtils.isNotBlank(dto.getMoney().getCurrency()) && Objects.nonNull(dto.getMoney().getAmount()), "money不能为空");
        Assert.warnIsTrue(Objects.nonNull(dto.getOriginMoney()) && StringUtils.isNotBlank(dto.getOriginMoney().getCurrency()), "originMoney不能为空");
        Assert.warnIsTrue(Objects.nonNull(dto.getTargetMoney()) && StringUtils.isNotBlank(dto.getTargetMoney().getCurrency()), "targetMoney不能为空");
    }

    /**
     * 筛选出最佳费率
     *
     * @param dto
     * @param feeConfigList
     */
    private FeeConfig getBestMatchFeeConfig(OrderInfoDTO dto, List<FeeConfig> feeConfigList) {

        String originCurrency = dto.getOriginMoney().getCurrency();
        String targetCurrency = dto.getTargetMoney().getCurrency();
        // 先过滤出货币不匹配的费率配置
        List<FeeConfig> configList = feeConfigList.stream().filter(f -> (originCurrency.equals(f.getOriginCurrency()) || FeeConfigConstants.ALL.equals(f.getOriginCurrency()))
                && (targetCurrency.equals(f.getTargetCurrency()) || FeeConfigConstants.ALL.equals(f.getTargetCurrency()))).collect(Collectors.toList());

        Assert.errorNotEmpty(configList, "没有找到匹配的费率配置," + dto.getOrderId());

        // 过滤出为个人的配置列表
        List<FeeConfig> configTypeList = configList.stream().filter(f -> f.getConfigType().equals(FeeConfigTypeEnum.USER.getType())).collect(Collectors.toList());

        if (!CollectionUtils.isEmpty(configTypeList)) {
            return configTypeList.get(0);
        }

        List<FeeConfig> locationList = configList.stream().filter(f -> !f.getScope().equals(FeeConfigConstants.ALL)).collect(Collectors.toList());
        if (!CollectionUtils.isEmpty(locationList)) {
            return locationList.get(0);
        }

        return configList.get(0);
    }

    /**
     * 计算费率
     *
     * @param orderInfo
     * @param bestMatchFeeConfig
     */
    private FeeConfigResponse calculateFeeInfo(OrderInfoDTO orderInfo, FeeConfig bestMatchFeeConfig) {
        FeeConfigResponse feeConfigResponse = new FeeConfigResponse();

        Money money = orderInfo.getMoney();

        String currency = money.getCurrency();
        BigDecimal amount = money.getAmount();

        feeConfigResponse.setRateFee(Money.builder().currency(currency).amount(amount.multiply(bestMatchFeeConfig.getFeeRate())).build().decimalDeal());
        feeConfigResponse.setCutFee(Money.builder().currency(currency).amount(amount.multiply(bestMatchFeeConfig.getCutFeeRate())).build().decimalDeal());

        //提现金额低于起提金额
//        if (originMoney.getAmount().compareTo(bestMatchFeeConfig.getMinPayout().getAmount()) >= 0) {
//            serviceCharge.getLessThanMinPayoutFee().setAmount(BigDecimal.ZERO);
//        }
        Money fixFeeMoney = JSONObject.parseObject(bestMatchFeeConfig.getFixFee(), Money.class);
        feeConfigResponse.setFixFee(fixFeeMoney);
        BigDecimal finalAmount = feeConfigResponse.getRateFee().getAmount();
        if (fixFeeMoney.getCurrency().equals(currency)) {
            finalAmount = finalAmount.add(fixFeeMoney.getAmount());
        }

        // 最终手续费
        Money finalFee = Money.builder()
                .currency(currency)
                .amount(finalAmount)
                .build();

//        Money rateFee = Money.builder()
//                .currency(currency)
//                .amount(feeConfigResponse.getRateFee().getAmount().subtract(feeConfigResponse.getCutFee().getAmount()))
//                .build();
//        feeConfigResponse.setRateFee(rateFee);

        if (finalFee.getAmount().compareTo(BigDecimal.ZERO) < 0) {
            log.info("手续费小于0，手续费 : {}", finalFee.getAmount());
            finalFee.setAmount(BigDecimal.ZERO);
        }
        //后台设置了最大手续费 再去比较
//        if (bestMatchFeeConfig.getMaxFee().getAmount().compareTo(BigDecimal.ZERO) > 0
//                && finalFee.getAmount().compareTo(feeConfigResponse.getMaxFee().getAmount()) > 0) {
//            Money cutFee = feeConfigResponse.getCutFee();
//            cutFee.setAmount(cutFee.getAmount().add(finalFee.getAmount().subtract(feeConfigResponse.getMaxFee().getAmount())));
//
//            finalFee = finalFee.copyOnWrite(feeConfigResponse.getMaxFee().getAmount());
//        }

        feeConfigResponse.setFinalFee(finalFee);
        return feeConfigResponse;
    }
}
