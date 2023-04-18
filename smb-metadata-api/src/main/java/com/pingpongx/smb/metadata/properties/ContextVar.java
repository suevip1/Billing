package com.pingpongx.smb.metadata.properties;

import com.pingpongx.smb.metadata.AbstractVariable;
import com.pingpongx.smb.metadata.VariableDef;

public class ContextVar extends AbstractVariable {
    public static ContextVar of(String key){
        ContextVar ret = new ContextVar();
        ret.setPath(key);
        return ret;
    }
}
