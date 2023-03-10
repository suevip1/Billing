package com.pingpongx.smb.fee.dependency.convert;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.pingpongx.flowmore.cloud.base.server.filter.ActionContext;
import com.pingpongx.smb.fee.dal.dataobject.FeeBaseDo;
import org.springframework.beans.BeanUtils;

import java.io.IOException;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Consumer;
import java.util.stream.Collectors;

public class ConvertUtil {

    public static <Do extends FeeBaseDo, Dto extends Serializable> Dto toDto(Do dataObject, Class<Dto> target) {
        ActionContext.getInstance().getContext().getUserId();
        //TODO : ignore properties List;
        List<String> ignore = new ArrayList<>();
        try {
            Dto ret = target.newInstance();
            BeanUtils.copyProperties(dataObject, ret, ignore.toArray(new String[]{}));
            return ret;
        } catch (InstantiationException e) {
            throw new RuntimeException(e);
        } catch (IllegalAccessException e) {
            throw new RuntimeException(e);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public static <Do extends FeeBaseDo, Dto extends Serializable> Dto toDto(Do dataObject, Class<Dto> target, Consumer<Dto> dtoConsumer) {
        Dto dto = toDto(dataObject, target);
        dtoConsumer.accept(dto);
        return dto;
    }

    public static <Do extends FeeBaseDo, Dto extends Serializable> List<Dto> toDtoList(List<Do> dataObjects, Class<Dto> target) {
        return dataObjects.stream().map(d -> toDto(d, target)).collect(Collectors.toList());
    }

    public static <Do extends FeeBaseDo, Dto extends Serializable> List<Do> toDoList(List<Dto> dataObjects, Class<Do> target) {
        return dataObjects.stream().map(d -> toDo(d, target)).collect(Collectors.toList());
    }

    public static <Do extends FeeBaseDo, Dto extends Serializable> Do toDo(Dto dataObject, Class<Do> target, Consumer<Do> consumer) {
        String userId = ActionContext.getInstance().getContext().getUserId();
        //TODO : ignore properties List;
        List<String> ignore = new ArrayList<>();
        try {
            Do ret = target.newInstance();
            BeanUtils.copyProperties(dataObject, ret, ignore.toArray(new String[]{}));
            ret.setUpdatedBy(userId);
            if (ret.getId() == null) {
                ret.setCreatedBy(userId);
            }
            consumer.accept(ret);
            return ret;
        } catch (InstantiationException e) {
            throw new RuntimeException(e);
        } catch (IllegalAccessException e) {
            throw new RuntimeException(e);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public static <Do extends FeeBaseDo, Dto extends Serializable> Do toDo(Dto dataObject, Class<Do> target) {
        String userId = ActionContext.getInstance().getContext().getUserId();
        //TODO : ignore properties List;
        List<String> ignore = new ArrayList<>();
        try {
            Do ret = target.newInstance();
            BeanUtils.copyProperties(dataObject, ret, ignore.toArray(new String[]{}));
            ret.setUpdatedBy(userId);
            if (ret.getId() == null) {
                ret.setCreatedBy(userId);
            }
            return ret;
        } catch (InstantiationException e) {
            throw new RuntimeException(e);
        } catch (IllegalAccessException e) {
            throw new RuntimeException(e);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }


    private static final ObjectMapper OBJECT_MAPPER = new ObjectMapper();

    public static <T> T parse(String jsonString, Class<T> clazz) {
        try {
            return OBJECT_MAPPER.readValue(jsonString, clazz);
        } catch (IOException var3) {
            throw new RuntimeException(var3);
        }
    }


    public static String toJSONString(Object obj) {
        try {
            return OBJECT_MAPPER.writeValueAsString(obj);
        } catch (JsonProcessingException var2) {
            throw new RuntimeException(var2);
        }
    }

}
