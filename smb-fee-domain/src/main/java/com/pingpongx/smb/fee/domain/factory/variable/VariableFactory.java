package com.pingpongx.smb.fee.domain.factory.variable;

import com.pingpongx.smb.fee.api.dtos.expr.ExprDto;
import com.pingpongx.smb.fee.dal.dataobject.ConfiguredVariableDef;
import com.pingpongx.smb.metadata.VariableDef;
import com.pingpongx.smb.metadata.properties.RequestVar;
import org.springframework.stereotype.Component;


public class VariableFactory {
    public static VariableDef load(ConfiguredVariableDef conf){
        //TODO
        VariableDef variableDef = new RequestVar();
        return variableDef;
    }
}
