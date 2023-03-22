package com.pingpongx.smb.metadata;

public interface Extractor {
    String doExtract(VariableDef var, Object source);
}
