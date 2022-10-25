package com.pingpongx.smb.fee.server.service;

import com.pingpongx.smb.fee.common.resp.ExchangeRateResponse;

import java.util.List;

/**
 * @author wangcheng
 * @Description TODO
 * @createTime 2022年07月05日 11:20:00
 */
public interface FxExchangeService {

    List<ExchangeRateResponse> getAmountEstimateExchangeRate();
}
