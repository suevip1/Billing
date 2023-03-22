package com.pingpongx.smb.fee.api.dtos.expr;

import com.alibaba.fastjson2.annotation.JSONType;
import lombok.Data;

@Data
@JSONType(typeName = "AXpB",typeKey = "type")
public class AXpBDto extends ExprDto {
    String a;
    String b;
    String varCode;
    String type = "AXpB";
}
