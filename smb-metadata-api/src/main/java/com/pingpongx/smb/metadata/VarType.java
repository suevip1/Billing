package com.pingpongx.smb.metadata;

import com.pingpongx.smb.metadata.metric.ContextVarExtractor;
import com.pingpongx.smb.metadata.metric.MetricVar;
import com.pingpongx.smb.metadata.metric.MetricVarDefFactory;
import com.pingpongx.smb.metadata.metric.RedisMetricExtractor;
import com.pingpongx.smb.metadata.properties.ContextVar;
import com.pingpongx.smb.metadata.properties.JavaReflectionExtractor;
import com.pingpongx.smb.metadata.properties.RequestVar;
import com.pingpongx.smb.metadata.properties.RequestVarDefFactory;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public enum VarType {

    Request("Request", RequestVar.class,new JavaReflectionExtractor(),new RequestVarDefFactory()),
    Metric("Metric", MetricVar.class,new RedisMetricExtractor(),new MetricVarDefFactory()),
    Context("Context",ContextVar.class,new ContextVarExtractor(),null);
    private static Map<Class<? extends VariableDef>, VarType> classMap = new HashMap<>();
    private static Map<String, VarType> typeMap = new HashMap<>();
    String code;
    Class<? extends VariableDef> clazz;

    Extractor extractor;
    VarDefFactory defFactory;

    public String getCode() {
        return code;
    }

    public Extractor getExtractor() {
        return extractor;
    }

    public Class<? extends VariableDef> getClazz() {
        return clazz;
    }

    public VarDefFactory getDefFactory() {
        return defFactory;
    }

    public void setDefFactory(VarDefFactory defFactory) {
        this.defFactory = defFactory;
    }

    VarType(String code, Class<? extends VariableDef> clazz, Extractor extractor, VarDefFactory factory) {
        this.code = code;
        this.clazz = clazz;
        this.extractor = extractor;
        this.defFactory = factory;
    }
    public static VarType classOf(Class<? extends VariableDef> clazz){
        VarType ret = classMap.get(clazz);
        if (ret == null){
            ret = Arrays.stream(VarType.values())
                    .filter(varType -> varType.getClazz().equals(clazz))
                    .findFirst().get();
            classMap.put(clazz,ret);
        }
        return ret;
    }

    public static VarType typeOf(String type){
        VarType ret = typeMap.get(type);
        if (ret == null){
            ret = Arrays.stream(VarType.values())
                    .filter(varType -> varType.getCode().equals(type))
                    .findFirst().orElse(null);
            typeMap.put(type,ret);
        }
        return ret;
    }
}
