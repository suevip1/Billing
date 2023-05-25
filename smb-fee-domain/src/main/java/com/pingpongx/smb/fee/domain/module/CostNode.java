package com.pingpongx.smb.fee.domain.module;

import com.pingpongx.smb.component.enums.BuEnum;
import com.pingpongx.smb.metadata.Identified;
import lombok.Data;

@Data
public class CostNode implements Identified {
    String code;
    String displayName;
    BuEnum bizLine;

    @Override
    public String identify() {
        return code;
    }
}
