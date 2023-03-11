package com.pingpongx.smb.fee.domain.module;

import com.pingpongx.smb.fee.domain.enums.BizLine;
import com.pingpongx.smb.metadata.Identified;
import lombok.Data;

@Data
public class CostNode implements Identified {
    String code;
    String displayName;
    BizLine bizLine;

    @Override
    public String identify() {
        return code;
    }
}
