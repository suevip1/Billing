package com.pingpongx.smb.fee.dal.dataobject;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

@Data
@EqualsAndHashCode(callSuper = true)
@Accessors(chain = true)
public class CostNodeDo extends FeeBaseDo {
    String code;
    String displayName;
    String bizLine;
}
