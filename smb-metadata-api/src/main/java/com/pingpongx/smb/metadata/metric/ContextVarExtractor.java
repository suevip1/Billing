package com.pingpongx.smb.metadata.metric;

import com.pingpongx.smb.metadata.Extractor;
import com.pingpongx.smb.metadata.VariableDef;
import com.pingpongx.smb.metadata.properties.JavaReflectionExtractor;

public class ContextVarExtractor extends JavaReflectionExtractor {

    /***
     *
     * @param var
     * @param source
     * @return
     */
    @Override
    public String doExtract(VariableDef var, Object source) {
        Object current = getAttr(source,"params");
        current = getAttr(current,var.getPath());
        return current.toString();
    }
}
