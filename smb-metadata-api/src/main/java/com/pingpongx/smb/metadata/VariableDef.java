package com.pingpongx.smb.metadata;

public interface VariableDef {
    String getPath();
    String getCode();
    boolean isNum();
    String getNamespace();
    default Extractor extractor(){
        VarType type = VarType.classOf(this.getClass());
        return type.getExtractor();
    }
}
