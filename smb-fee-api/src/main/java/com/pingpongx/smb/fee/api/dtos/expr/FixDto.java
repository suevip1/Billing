package com.pingpongx.smb.fee.api.dtos.expr;

import com.alibaba.fastjson2.annotation.JSONType;
import lombok.Data;

@Data
@JSONType(typeName = "Fix",typeKey = "type")
public class FixDto extends ExprDto {
    double fix;
    String type = "Fix";
}
