package com.pingpongx.smb.fee.domain.module;

import com.pingpongx.smb.component.enums.BuEnum;
import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class Contract {
    String code;
    String displayName;
    BuEnum bizLine;
    Long priority;

}
