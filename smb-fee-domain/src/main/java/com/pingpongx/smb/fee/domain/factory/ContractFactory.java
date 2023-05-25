package com.pingpongx.smb.fee.domain.factory;

import com.pingpongx.smb.component.enums.BuEnum;
import com.pingpongx.smb.fee.domain.module.Contract;

/***
 * 预留 合同下挂费用项
 */
public class ContractFactory {
    public static Contract defaultContract(){
        Contract contract = Contract.builder()
                .code("PPCustomContract").bizLine(BuEnum.FM).displayName("PingPong对客默认计费合同").priority(0L)
                .build();
        return contract;
    }
}
