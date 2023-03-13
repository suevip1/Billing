package com.pingpongx.smb.fee.domain.factory.variable;

import com.pingpongx.smb.fee.api.dtos.expr.ExprDto;
import com.pingpongx.smb.fee.dal.dataobject.ConfiguredVariableDef;
import com.pingpongx.smb.fee.dependency.convert.ConvertUtil;
import com.pingpongx.smb.metadata.VarType;
import com.pingpongx.smb.metadata.VariableDef;
import com.pingpongx.smb.metadata.properties.RequestVar;
import org.springframework.stereotype.Component;


public class VariableFactory {
    public static VariableDef load(ConfiguredVariableDef conf){
        String type = conf.getType();
        VarType varType = VarType.typeOf(type);
        VariableDef variableDef = ConvertUtil.to(conf, varType.getClazz());
        return variableDef;
    }
}
