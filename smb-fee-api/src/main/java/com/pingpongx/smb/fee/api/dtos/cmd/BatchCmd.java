package com.pingpongx.smb.fee.api.dtos.cmd;

import com.pingpongx.smb.metadata.Identified;
import lombok.Data;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

@Data
public class BatchCmd implements Serializable, Identified {

    private static final long serialVersionUID = 8061568762858026972L;

    String batchNo;

    List<BillingRequest> requests;

    @Override
    public String identify() {
        return batchNo;
    }
}
