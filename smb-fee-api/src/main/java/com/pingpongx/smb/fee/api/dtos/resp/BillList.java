package com.pingpongx.smb.fee.api.dtos.resp;

import lombok.Data;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Data
public class BillList implements Serializable {
    private static final long serialVersionUID = 8063154680261469672L;

    String batchNo;
    List<Bill> feeResult = new ArrayList<>();

}
