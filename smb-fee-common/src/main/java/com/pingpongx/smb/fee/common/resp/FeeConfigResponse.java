package com.pingpongx.smb.fee.common.resp;

import com.pingpongx.business.common.dto.Money;
import lombok.Data;
import java.io.Serializable;


/**
 * @author wangcheng
 * @Description TODO
 * @createTime 2022年06月28日 16:08:00
 */
@Data
public class FeeConfigResponse implements Serializable {
    private static final long serialVersionUID = -1663667928528575200L;

    private Money cutFee;
    private Money rateFee;
    private Money couponFee;
    private Money finalFee;
    private Money fixFee;
    private Money maxFee;


}
