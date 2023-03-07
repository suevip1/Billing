package com.pingpongx.smb.metadata.properties;

import com.pingpongx.smb.metadata.Extractor;
import com.pingpongx.smb.metadata.VariableDef;
import org.springframework.util.ReflectionUtils;
import org.springframework.util.StringUtils;

import java.lang.reflect.Field;

public class JavaReflectionExtractor implements Extractor {

    private static String Split = "/";

    /***
     *
     * @param var
     * @param source
     * @return
     */
    @Override
    public Object doExtract(VariableDef var, Object source) {
        String path = var.getPath();
        if (path == null){
            return source;
        }
        String[] paths = path.split(Split);
        Object current = source;
        for (int i = 0 ; i<paths.length ; i++){
            String attr = paths[i];
            if (StringUtils.isEmpty(attr)){
                continue;
            }
            current = getAttr(current,attr);
            if (current == null){
                return null;
            }
        }
        return current;
    }

    private Object getAttr(Object data,String attr){
        Field f = ReflectionUtils.findField(data.getClass(), attr);
        if (f == null){
            return null;
        }
        ReflectionUtils.makeAccessible(f);
        return ReflectionUtils.getField(f, data);
    }
}
