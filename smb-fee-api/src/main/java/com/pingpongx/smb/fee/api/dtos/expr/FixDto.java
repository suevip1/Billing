package com.pingpongx.smb.fee.api.dtos.expr;

import com.alibaba.fastjson2.annotation.JSONType;
import lombok.Data;

@Data
@JSONType(typeName = "FixDto")
public class FixDto extends ExprDto{
    double fix;
    String type = "Fix";

}
