package com.pingpongx.smb.metadata.qry;

import java.util.List;

public class GetByCode {
    String namespace;
    List<String> defCode;

    public String getNamespace() {
        return namespace;
    }

    public void setNamespace(String namespace) {
        this.namespace = namespace;
    }

    public List<String> getDefCode() {
        return defCode;
    }

    public void setDefCode(List<String> defCode) {
        this.defCode = defCode;
    }
}
