package com.pingpongx.smb.metadata;

public abstract class AbstractVariable implements VariableDef {
    String path;
    String code;
    String isNum;
    String namespace;

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public boolean isNum() {
        return Boolean.valueOf(isNum);
    }

    public void setIsNum(Boolean isNum) {
        this.isNum = isNum.toString();
    }

    public String getNamespace() {
        return namespace;
    }

    public void setNamespace(String namespace) {
        this.namespace = namespace;
    }

    @Override
    public String identify() {
        return getCode();
    }
}
