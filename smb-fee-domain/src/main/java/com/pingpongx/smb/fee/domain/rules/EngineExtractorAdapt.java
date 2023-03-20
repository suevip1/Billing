package com.pingpongx.smb.fee.domain.rules;

import com.pingpongx.smb.export.spi.AttrExtractor;
import com.pingpongx.smb.metadata.properties.JavaReflectionExtractor;

public class EngineExtractorAdapt extends JavaReflectionExtractor implements AttrExtractor {
    @Override
    public Object getAttr(Object data, String attrPath) {
        return super.getAttr(data, attrPath);
    }
}
