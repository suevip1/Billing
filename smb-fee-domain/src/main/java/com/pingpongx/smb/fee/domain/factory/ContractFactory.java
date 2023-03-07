package com.pingpongx.smb.fee.domain.factory;

import com.pingpongx.smb.fee.domain.entity.Contract;
import com.pingpongx.smb.fee.domain.enums.BizLine;

public class ContractFactory {
    public static Contract defaultContract(){
        Contract contract = Contract.builder()
                .code("PPCustomContract").bizLine(BizLine.Common).displayName("PingPong对客默认计费合同").priority(0L)
                .build();
        return contract;
    }
}
