package com.pingpongx.smb.fee.dal.dataobject;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import java.math.BigDecimal;
import java.util.Map;

/**
 * @author wangcheng
 * @Description TODO
 * @createTime 2022年06月28日 11:54:00
 */
@Data
@EqualsAndHashCode(callSuper = true)
@Accessors(chain = true)
public class CouponResultDo extends FeeBaseDo {

    private static final long serialVersionUID = 8061315687610264972L;

    String templateCode;
    Long couponId;
    BigDecimal credit;
    String creditType;


}
