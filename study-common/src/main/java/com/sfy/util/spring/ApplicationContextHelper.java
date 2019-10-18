package com.sfy.util.spring;

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;

/**
 * @Description: ApplicationContextHelper.java
 * @Date: 2016/04/08
 * @Author: sunfayun
 * @Version: 1.0
 */
public class ApplicationContextHelper implements ApplicationContextAware {

    private static ApplicationContext applicationContext;

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        ApplicationContextHelper.applicationContext = applicationContext;
    }

    public static <T> T popBean(Class<T> clazz) {
        if(applicationContext == null) return null;
        return applicationContext.getBean(clazz);
    }

    public static <T> T popBean(String name, Class<T> clazz) {
        if(applicationContext == null) return null;
        return applicationContext.getBean(name, clazz);
    }
}
