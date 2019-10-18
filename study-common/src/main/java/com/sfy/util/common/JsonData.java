package com.sfy.util.common;

import com.google.common.base.Preconditions;
import com.google.common.collect.Maps;
import lombok.ToString;

import java.util.Collection;
import java.util.Collections;
import java.util.Map;

/**
 * @Description: JsonData.java
 * @Date: 2016/04/08
 * @Author: sunfayun
 * @Version: 1.0
 */
@ToString
public class JsonData {

    private boolean status;
    private String msg;
    private Object data;
    private Integer code;

    private JsonData(boolean status) {
        this.status = status;
    }

    /**
     * eg.
     *
     * <pre>
     *      {
     *          "status" : false,
     *          "msg" : "错误的id，修改失败",
     *          "code" : 1
     *      }
     * </pre>
     *
     * @param message
     * @param code
     * @return JsonData
     */
    public static JsonData error(String message, Integer code) {
        JsonData result =  new JsonData(false);
        result.msg = message ;
        result.code = code;
        return result;
    }

    /**
     * eg.
     *
     * <pre>
     *      {
     *          "status" : false,
     *          "msg" : "错误信息"
     *      }
     * </pre>
     *
     * @param message
     * @return JsonData
     */
    public static JsonData error(String message) {
        return error(message,null);
    }

    /**
     * eg.
     *
     * <pre>
     *      {
     *          "status" : false
     *      }
     * </pre>
     * @return JsonData
     */
    public static JsonData error() {
        return error(null,null);
    }

    /**
     * eg.
     *
     * <pre>
     *      {
     *          "status" : false
     *          "code" : 1
     *      }
     * </pre>
     *
     * @param code 错误码
     * @return JsonData
     */
    public static JsonData error(Integer code) {
        return error(null,code);
    }

    /**
     * eg.
     *
     * <pre>
     *      {
     *          "status" : true,
     *          "data": {
     *                      "encodedRID": "2916181129",
     *                      "operator": "gambol2",
     *                      "createTime": 1411363837776,
     *                      "rescueStatus": 1,
     *                      "pFunction": "free",
     *                      "departure": "北京",
     *                      "arrive": "大连"
     *          },
     *          "msg";"修改成功"
     *      }
     * </pre>
     *
     * @param object 对象
     * @param msg
     * @return JsonData
     */
    public static JsonData success(Object object, String msg) {
        JsonData result =  new JsonData(true);
        result.data = object;
        result.msg = msg;
        return result;
    }

    /**
     * eg.
     *
     * <pre>
     *      {
     *          "status" : true,
     *          "data": {
     *                      "encodedRID": "2916181129",
     *                      "operator": "gambol2",
     *                      "createTime": 1411363837776,
     *                      "rescueStatus": 1,
     *                      "pFunction": "free",
     *                      "departure": "北京",
     *                      "arrive": "大连"
     *          }
     *      }
     * </pre>
     *
     * @param object 对象
     * @return JsonData
     */
    public static JsonData success(Object object) {
        return success(object,null);
    }

    /**
     * eg.
     *
     * <pre>
     *      {
     *          "status" : true,
     *          "msg";"修改成功"
     *      }
     * </pre>
     *
     * @param msg
     * @return JsonData
     */
    public static JsonData success(String msg) {
        return success(null,msg);
    }

    /**
     * eg.
     *
     * <pre>
     *      {
     *          "status" : true
     *      }
     * </pre>
     *
     * @return JsonData
     */
    public static JsonData success() {
        return success(null,null);
    }

    /**
     * 返回分页数据，包括content，totalRows,msg
     * @param content
     * @param totalRows
     * @param msg
     * @param <T>
     * @return
     */
    public static <T> JsonData list(Collection<T> content, Integer totalRows, String msg) {
        Preconditions.checkNotNull(content);  // fast fail
        JsonData result =  new JsonData(true);
        Map<String,Object> map = Maps.newHashMap();
        map.put("content",content);
        if (totalRows == null) {
            map.put("totalRows",content.size());
        } else {
            map.put("totalRows",totalRows);
        }
        result.msg = msg;
        result.data = map;
        return result;
    }

    /**
     * 返回带msg的集合数据
     * @param content
     * @param msg
     * @param <T>
     * @return
     */
    public static <T> JsonData list(Collection<T> content, String msg) {
        return list(content,null,msg);
    }

    /**
     * 返回集合数据，msg,code为空
     * @param content
     * @param <T>
     * @return
     */
    public static <T> JsonData list(Collection<T> content) {
        return list(content,null,null);
    }

    /**
     * 返回分页数据，包括content，totalRows
     * @param content
     * @param totalRows
     * @param <T>
     * @return
     */
    public static <T> JsonData list(Collection<T> content, Integer totalRows) {
        return list(content, totalRows, null);
    }

    /**
     * 返回空集合数据
     * eg.
     * <pre>
     *
     *  {
     *      "status": true,
     *      "data": {
     *          "content": [],
     *          "totalRows": 0
     *      }
     *  }
     * </pre>
     *
     * @param <T>
     * @return JsonData
     */
    public static <T> JsonData emptyList() {
        return list(Collections.emptySet());
    }

    public boolean getStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }
}
