package com.pingpongx.smb.fee.api.dtos.resp;

import lombok.Data;
import org.joda.money.Money;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Data
public class CostItemResult implements Serializable {

    private static final long serialVersionUID = 8061568762858026972L;
    Boolean success;
    String itemCode;
    String itemName;
    Money fee;
    String failedReason;
}
