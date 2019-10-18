package com.sfy.util.bean;

import com.google.common.collect.Lists;
import com.google.common.collect.Maps;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;
import java.util.Collection;
import java.util.Collections;
import java.util.Map;
import java.util.Set;

import static com.google.common.base.Preconditions.checkNotNull;

/**
 * @Description: BeanValidator.java
 * @Date: 2016/04/08
 * @Author: sunfayun
 * @Version: 1.0
 */
public class BeanValidator {

    private static ValidatorFactory validatorFactory;

    static {
        /* 创建效验工厂 */
        validatorFactory = Validation.buildDefaultValidatorFactory();
    }

    /**
     * 对实体对象进行校验，
     *
     * @param t 实体对象
     * @return 验证失败信息
     */
    public static <T> Map<String, String> validate(T t, Class<?>... groups) {
        Validator validator = validatorFactory.getValidator();
        Set<ConstraintViolation<T>> validateResult = validator.validate(t, groups);
        if (validateResult.isEmpty()) {
            return Collections.emptyMap();
        } else {
            Map<String, String> errors = Maps.newLinkedHashMap();
            for (ConstraintViolation<T> constraintViolation : validateResult) {
                errors.put(constraintViolation.getPropertyPath().toString(), constraintViolation.getMessage());
            }
            return errors;
        }
    }

    /**
     * 校验对象
     *
     * @param first  实体对象
     * @param others 其他对象
     * @return 验证失败信息
     * @see #validateForList
     */
    public static Map<String, String> validateForObjects(Object first, Object... others) {
        if (others == null || others.length == 0) {
            return validate(first);
        }
        return validateForList(Lists.asList(first, others));
    }


    /**
     * 支持对集合对象的校验
     *
     * @param collection
     * @return
     */
    public static Map<String, String> validateForList(Collection<?> collection) {
        checkNotNull(collection);
        Map<String, String> errors;
        for (Object object : collection) {
            errors = validate(object);
            if (!errors.isEmpty()) {
                return errors;
            }
        }
        return Collections.emptyMap();
    }


    /**
     * 对实体对象进行校验，返回的错误message添加类名
     *
     * @param t 实体对象
     * @return 验证失败信息
     */
    public static <T> Map<String, String> validateWithTrace(T t, Class<?>... groups) {
        Validator validator = validatorFactory.getValidator();
        Set<ConstraintViolation<T>> validateResult = validator.validate(t, groups);
        if (validateResult.isEmpty()) {
            return Collections.emptyMap();
        } else {
            Map<String, String> errors = Maps.newLinkedHashMap();
            for (ConstraintViolation<T> constraintViolation : validateResult) {
                String errorMsg = constraintViolation.getMessage();
                String errorClass = constraintViolation.getRootBeanClass().getName();
                String errorTrace = "message:\"" + errorMsg + "\" class:\"" + errorClass + "\";";
                errors.put(constraintViolation.getPropertyPath().toString(), errorTrace);
            }
            return errors;
        }
    }

    /**
     * 支持对集合对象的校验，返回的错误message添加类名
     *
     * @param collection
     * @return
     */
    public static Map<String, String> validateForListWithTrace(Collection<?> collection) {
        checkNotNull(collection);
        Map<String, String> errors;
        for (Object object : collection) {
            errors = validateWithTrace(object);
            if (!errors.isEmpty()) {
                return errors;
            }
        }
        return Collections.emptyMap();
    }

    /**
     * 校验对象，返回的错误message添加类名
     *
     * @param first  实体对象
     * @param others 其他对象
     * @return 验证失败信息
     * @see #validateForList
     */
    public static Map<String, String> validateForObjectsWithTrace(Object first, Object... others) {
        if (others == null || others.length == 0) {
            return validateWithTrace(first);
        }
        return validateForListWithTrace(Lists.asList(first, others));
    }

}
