package com.sfy.util.common;

import com.google.common.collect.Lists;
import com.google.common.collect.Maps;

import javax.validation.*;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * 根据通用校验框架提供校验工具类，需要校验的实体对象添加校验注解规则就可以了。
 */
public class AnnotationValidatorSupport {
    
    private static ValidatorFactory validatorFactory;

    static{
        /* 创建效验工厂 */
         validatorFactory = Validation.buildDefaultValidatorFactory(); 
    }
    
    /**
     * 对实体对象进行校验，
     * @param t 实体对象
     * @return  验证失败信息
     */
    public static <T> Map<String,String>  validate(T t,Class<?>... groups){
                Map<String,String> errors=new LinkedHashMap<String, String>();
                Validator validator = validatorFactory.getValidator(); //Validator应该是否线程不安全的，还有待检查
                /* 将类型装载效验 */
                Set<ConstraintViolation<T>> set = validator.validate(t,groups); 
                
                for (ConstraintViolation<T> constraintViolation : set)
                    errors.put(constraintViolation.getPropertyPath().toString(),constraintViolation.getMessage());
                
                return errors;

    }

    /**
     * 返回真实的错误
     * @param object
     * @param groups
     * @return
     */
    public static Set validateWithoutException(Object object, Class<?>... groups) {
        Validator validator = validatorFactory.getValidator(); //Validator应该是否线程不安全的，还有待检查
        Set constraintViolations = validator.validate(object, groups);
        return constraintViolations;
    }

    /**
     * 调用JSR303的validate方法, 验证失败时抛出ConstraintViolationException, 而不是返回constraintViolations.
     */
    public static void validateWithException(Object object, Class<?>... groups)
      throws ConstraintViolationException {
        Validator validator = validatorFactory.getValidator(); //Validator应该是否线程不安全的，还有待检查
        Set constraintViolations = validator.validate(object, groups);
        if (!constraintViolations.isEmpty()) {
            throw new ConstraintViolationException(constraintViolations);
        }
    }

    /**
     * 辅助方法, 转换ConstraintViolationException中的Set<ConstraintViolations>中为List<message>.
     */
    public static List<String> extractMessage(ConstraintViolationException e) {
        return extractMessage(e.getConstraintViolations());
    }

    /**
     * 辅助方法, 转换Set<ConstraintViolation>为List<message>
     */
    public static List<String> extractMessage(Set<? extends ConstraintViolation> constraintViolations) {
        List<String> errorMessages = Lists.newArrayList();
        for (ConstraintViolation violation : constraintViolations) {
            errorMessages.add(violation.getMessage());
        }
        return errorMessages;
    }

    /**
     * 辅助方法, 转换ConstraintViolationException中的Set<ConstraintViolations>为Map<property, message>.
     */
    public static Map<String, String> extractPropertyAndMessage(ConstraintViolationException e) {
        return extractPropertyAndMessage(e.getConstraintViolations());
    }

    /**
     * 辅助方法, 转换Set<ConstraintViolation>为Map<property, message>.
     */
    public static Map<String, String> extractPropertyAndMessage(Set<? extends ConstraintViolation> constraintViolations) {
        Map<String, String> errorMessages = Maps.newHashMap();
        for (ConstraintViolation violation : constraintViolations) {
            errorMessages.put(violation.getPropertyPath().toString(), violation.getMessage());
        }
        return errorMessages;
    }

    /**
     * 辅助方法, 转换ConstraintViolationException中的Set<ConstraintViolations>为List<propertyPath message>.
     */
    public static List<String> extractPropertyAndMessageAsList(ConstraintViolationException e) {
        return extractPropertyAndMessageAsList(e.getConstraintViolations(), " ");
    }

    /**
     * 辅助方法, 转换Set<ConstraintViolations>为List<propertyPath message>.
     */
    public static List<String> extractPropertyAndMessageAsList(Set<? extends ConstraintViolation> constraintViolations) {
        return extractPropertyAndMessageAsList(constraintViolations, " ");
    }

    /**
     * 辅助方法, 转换ConstraintViolationException中的Set<ConstraintViolations>为List<propertyPath + separator + message>.
     */
    public static List<String> extractPropertyAndMessageAsList(ConstraintViolationException e, String separator) {
        return extractPropertyAndMessageAsList(e.getConstraintViolations(), separator);
    }

    /**
     * 辅助方法, 转换Set<ConstraintViolation>为List<propertyPath + separator + message>.
     */
    public static List<String> extractPropertyAndMessageAsList(Set<? extends ConstraintViolation> constraintViolations,
      String separator) {
        List<String> errorMessages = Lists.newArrayList();
        for (ConstraintViolation violation : constraintViolations) {
            errorMessages.add(violation.getPropertyPath() + separator + violation.getMessage());
        }
        return errorMessages;
    }

    public static Map<String, String> validateForMultiObject(List<?> list) {
        Map<String, String> errors = Maps.newLinkedHashMap();
        for (Object t1 : list) {
            errors = validate(t1);
            if (!errors.isEmpty()) {
                break;
            }
        }
        return errors;
    }
}
