package com.pingpongx.smb.fee.common.dto;

import lombok.Data;

import java.io.Serializable;
import java.math.BigDecimal;

/**
 * @author wangcheng
 * @Description TODO
 * @createTime 2022年07月05日 10:22:00
 */
@Data
public class FxGuidePriceExchangeDTO implements Serializable {

    private static final long serialVersionUID = 1078006496856496043L;

    private BigDecimal buy;

    private String id;

    private String modified;

    private String name;

    private BigDecimal offer;

    private String symbol;

}
