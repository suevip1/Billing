package com.pingpongx.smb.fee.domain.module;

import com.pingpongx.smb.fee.domain.enums.BizLine;
import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class Contract {
    String code;
    String displayName;
    BizLine bizLine;
    Long priority;

}
