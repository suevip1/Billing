package com.pingpongx.smb.metadata.properties;

import com.pingpongx.smb.metadata.Extractor;
import com.pingpongx.smb.metadata.VariableDef;
import org.springframework.util.ReflectionUtils;
import org.springframework.util.StringUtils;

import java.lang.reflect.Field;
import java.util.Map;

public class JavaReflectionExtractor implements Extractor {

    private static String Split = "/";

    /***
     *
     * @param var
     * @param source
     * @return
     */
    @Override
    public String doExtract(VariableDef var, Object source) {
        if (source == null){
            return "";
        }
        String path = var.getPath();
        if (path == null){
            return source.toString();
        }
        String[] paths = path.split(Split);
        Object current = source;
        current = getAttr(current,"request");
        current = getAttr(current,"orderInfo");
        for (int i = 0 ; i<paths.length ; i++){
            String attr = paths[i];
            if (StringUtils.isEmpty(attr)){
                continue;
            }
            current = getAttr(current,attr);
            if (current == null){
                return "";
            }
        }
        return current.toString();
    }

    protected Object getAttr(Object data,String attr){
        if (data == null){
            return "";
        }
        if (data instanceof Map){
            return ((Map<?, ?>) data).get(attr);
        }
        Field f = ReflectionUtils.findField(data.getClass(), attr);
        if (f == null){
            return "";
        }
        ReflectionUtils.makeAccessible(f);
        return ReflectionUtils.getField(f, data);
    }
}
