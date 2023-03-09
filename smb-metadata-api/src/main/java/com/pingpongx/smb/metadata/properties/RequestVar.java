package com.pingpongx.smb.metadata.properties;

import com.pingpongx.smb.metadata.AbstractVariable;
public class RequestVar extends AbstractVariable {
    String sourceType;

    public String getSourceType() {
        return sourceType;
    }

    public void setSourceType(String sourceType) {
        this.sourceType = sourceType;
    }

    @Override
    public String identify() {
        return getCode();
    }
}
