package com.pingpongx.smb.fee.api.dtos.cmd.coupon;

import com.pingpongx.smb.fee.api.dtos.cmd.BaseRequest;
import com.pingpongx.smb.fee.api.dtos.cmd.common.CouponInfo;
import com.pingpongx.smb.fee.api.dtos.cmd.common.RateInfo;
import com.pingpongx.smb.fee.api.dtos.cmd.coupon.CouponOrderInfo;
import com.pingpongx.smb.metadata.Identified;
import lombok.Data;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

@Data
public class CouponRequest extends BaseRequest<CouponOrderInfo> {
}
