package com.sfy.util.json;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.databind.ser.impl.SimpleFilterProvider;
import com.fasterxml.jackson.databind.util.JSONPObject;
import com.google.common.base.Strings;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @Description: JsonMapper.java
 * @Date: 2016/04/08
 * @Author: sunfayun
 * @Version: 1.0
 */
public class JsonMapper {

    private static Logger logger = LoggerFactory.getLogger(JsonMapper.class);
    private static ObjectMapper objectMapper = new ObjectMapper();

    static {
        //config
        objectMapper.disable(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES);
        objectMapper.configure(SerializationFeature.FAIL_ON_EMPTY_BEANS, false);
        objectMapper.setFilters(new SimpleFilterProvider().setFailOnUnknownId(false));
        objectMapper.setSerializationInclusion(JsonInclude.Include.NON_NULL);
    }

    /**
     * Object => String
     * 对象转换为Json字符串
     * @param src
     * @return
     */
    public static <T> String mapString(T src) {
        if (src == null) {
            return null;
        }

        try {
            return src instanceof String ? (String) src : objectMapper.writeValueAsString(src);
        } catch (Exception e) {
            logger.warn("Parse Object to String error", e);
            return null;
        }
    }

    /**
     * Object => String pretty
     * 对象转换为格式化后的Json字符串
     * @param src
     * @return
     */
    public static <T> String mapStringPretty(T src) {
        if (src == null) {
            return null;
        }

        try {
            return src instanceof String ? (String) src : objectMapper.writerWithDefaultPrettyPrinter().writeValueAsString(src);
        } catch (Exception e) {
            logger.warn("Parse Object to String pretty error", e);
            return null;
        }
    }

    /**
     * Object => byte[]
     * 对象转为字节数组
     * @param src
     * @return
     */
    public static <T> byte[] mapBytes(T src) {
        if (src == null) {
            return null;
        }

        try {
            return src instanceof byte[] ? (byte[]) src : objectMapper.writeValueAsBytes(src);
        } catch (Exception e) {
            logger.warn("Parse Object to byte[] error", e);
            return null;
        }
    }

    /**
     * String => Object
     * 字符串转换为对象
     * @param str
     * @param clazz
     * @return
     */
    public static <T> T mapObject(String str, Class<T> clazz) {
        if (Strings.isNullOrEmpty(str) || clazz == null) {
            return null;
        }
        try {
            return clazz.equals(String.class) ? (T)str : objectMapper.readValue(str, clazz);
        } catch (Exception e) {
            logger.warn(String.format("Parse String to Object error\nString:%s\nClass<T>:%s", str, clazz.getName()), e);
            return null;
        }
    }

    /**
     * byte[] => Object
     * 字节数组转换为对象
     * @param bytes
     * @param clazz
     * @return
     */
    public static <T> T mapObject(byte[] bytes, Class<T> clazz) {
        if (bytes == null || clazz == null) {
            return null;
        }
        try {
            return clazz.equals(byte[].class) ? (T)bytes : objectMapper.readValue(bytes, clazz);
        } catch (Exception e) {
            logger.warn(String.format("Parse byte[] to Object error\nbyte[]:%s\nClass<T>:%s", bytes, clazz.getName()), e);
            return null;
        }
    }

    /**
     * String => Object
     * 字符串转为对象
     * @param str
     * @param typeReference
     * @return
     */
    public static <T> T mapObject(String str, TypeReference<T> typeReference) {
        if (Strings.isNullOrEmpty(str) || typeReference == null) {
            return null;
        }
        try {
            return (T) (typeReference.getType().equals(String.class) ? str : objectMapper.readValue(str, typeReference));
        } catch (Exception e) {
            logger.warn(String.format("Parse String to Object error\nString:%s\nTypeReference<T>:%s", str, typeReference.getType()), e);
            return null;
        }
    }

    /**
     * byte[] => Object
     * 字节数组转为对象
     * @param bytes
     * @param typeReference
     * @return
     */
    public static <T> T mapObject(byte[] bytes, TypeReference<T> typeReference) {
        if (bytes == null || typeReference == null) {
            return null;
        }
        try {
            return (T) (typeReference.getType().equals(byte[].class) ? bytes : objectMapper.readValue(bytes, typeReference));
        } catch (Exception e) {
            logger.warn(String.format("Parse byte[] to Object error\nbyte[]:%s\nTypeReference<T>:%s", bytes, typeReference.getType()), e);
            return null;
        }
    }

    /**
     * Object -> JsonP String
     * Jsonp转换为对象
     * @param function
     * @param src
     * @param <T>
     * @return
     */
    public static <T>  String mapJSONPString(String function, T src) {
        return mapString(new JSONPObject(function, src));
    }

}
