package com.pingpongx.smb.fee.api.dtos.expr;

import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import lombok.Data;

import java.io.Serializable;

@Data
@JsonTypeInfo(use = JsonTypeInfo.Id.NAME, property = "type",visible = true)
@JsonSubTypes({
        @JsonSubTypes.Type(value = FixDto.class, name = "Fix"),
        @JsonSubTypes.Type(value = AXpBDto.class, name = "AXpB"),
        @JsonSubTypes.Type(value = MaxDto.class, name = "Max"),
        @JsonSubTypes.Type(value = MinDto.class, name = "Min"),
        @JsonSubTypes.Type(value = TierDto.class, name = "Tier")
})
public class ExprDto implements Serializable {
    String type;

}
