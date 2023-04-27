package com.pingpongx.smb.fee.api.dtos.cmd.coupon;

import com.pingpongx.smb.fee.api.dtos.cmd.trade.BillingRequest;
import com.pingpongx.smb.metadata.Identified;
import lombok.Data;

import java.io.Serializable;
import java.util.List;

@Data
public class BatchCmd implements Serializable, Identified {

    private static final long serialVersionUID = 8061568762858026972L;

    String batchNo;

    List<CouponRequest> requests;

    @Override
    public String identify() {
        return batchNo;
    }
}
