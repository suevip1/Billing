package com.pingpongx.smb.fee.common.resp;

import lombok.Builder;
import lombok.Data;

import java.io.Serializable;
import java.util.List;

/**
 * @author wangcheng
 * @Description TODO
 * @createTime 2022年07月06日 13:47:00
 */
@Builder
@Data
public class FinalExchangeRateResponse implements Serializable {
    private static final long serialVersionUID = 4089216582279181521L;

    private List<ExchangeRateResponse> data;

    private Long updateTime;
}
