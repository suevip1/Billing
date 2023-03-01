package com.pingpongx.smb.metadata;

public interface Extractor {
    Object doExtract(VariableDef var, Object source);
}
