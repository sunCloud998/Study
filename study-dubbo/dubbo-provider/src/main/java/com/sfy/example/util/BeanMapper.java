package com.sfy.example.util;

import org.dozer.DozerBeanMapper;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;

import static com.google.common.base.Preconditions.checkNotNull;

/**
 * @Description: BeanMapper.java
 * @Date: 2016/04/08
 * @Author: sunfayun
 * @Version: 1.0
 */
public class BeanMapper {

    private static DozerBeanMapper dozer = new DozerBeanMapper();

    /**
     * 基于Dozer转换对象的类型.
     */
    public static <T> T map(Object source, Class<T> destinationClass) {
        checkNotNull(source);
        checkNotNull(destinationClass);
        return dozer.map(source, destinationClass);
    }

    /**
     * 基于Dozer将对象A的值拷贝到对象B中.
     */
    public static void copy(Object source, Object destinationObject) {
        checkNotNull(source);
        checkNotNull(destinationObject);
        dozer.map(source, destinationObject);
    }

    /**
     * 基于dozer对两个不同的list进行匹配
     *
     * @param source
     * @param destinationClass
     * @param <T>
     * @param <U>
     * @return
     */
    public static <T, U> List<U> mapList(final List<T> source, final Class<U> destinationClass) {
        checkNotNull(source);
        final List<U> destList = new ArrayList<U>();
        for (T t : source) {
            destList.add(map(t, destinationClass));
        }
        return destList;
    }

    /**
     * 下划线——驼峰参数转换方法
     * 无需mapping注解
     * 自动处理驼峰和下划线类型的转换，支持：
     *      驼峰——>下划线
     *      下划线——>驼峰
     *      驼峰——>驼峰
     *      下划线——>下划线
     * 的转换。
     * 可能出现问题的场景：
     * 在同一个类的内部，存在两个或更多的这样的参数——将参数名的大写字母均转为小写并将下划线去除后，剩下的字符串内容相同。
     * 会导致这几个参数都被赋上相同的某个值。
     * 异常处理：
     * 当source和destination的参数不能一一对应的时候会出现异常
     * 处理方法：
     * 吞掉。该方法并不关注这种异常。
     *
     * @param source
     * @param destination
     * @throws Exception
     */
    public static void copyBetweenCamelUnderline(Object source, Object destination){
        checkNotNull(source);
        checkNotNull(destination);
        Method[] methodsSource = source.getClass().getMethods();
        for (Method methodSource : methodsSource) {
            copyValues(source, destination, methodSource, "get");
            copyValues(source, destination, methodSource, "is");
        }
    }

    private static void copyValues(Object source, Object destination, Method methodSource, String methodHead) {
        if (methodSource.getName().startsWith(methodHead)) {
            String sourceFieldName = methodSource.getName().substring(methodHead.length()).
                    replaceAll("_", "").toLowerCase();
            Method[] methodsDest = destination.getClass().getMethods();
            for (Method methodDest : methodsDest) {
                try {
                    if (methodDest.getName().startsWith("set")) {
                        String destFieldName = methodDest.getName().substring("set".length()).
                                replaceAll("_", "").toLowerCase();
                        if (sourceFieldName.equals(destFieldName)) {
                            if (methodSource.invoke(source).getClass().getClassLoader() != null) {
                                Object newInstance = methodDest.getParameterTypes()[0].newInstance();
                                copyBetweenCamelUnderline(methodSource.invoke(source), newInstance);
                                methodDest.invoke(destination, newInstance);
                            } else {
                                methodDest.invoke(destination, methodSource.invoke(source));
                            }
                        }
                    }
                } catch (Exception e) {
                    //ignore
                }
            }
        }
    }

}
