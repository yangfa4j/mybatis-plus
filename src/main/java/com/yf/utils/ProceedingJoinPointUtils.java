package com.yf.utils;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.reflect.MethodSignature;

import java.lang.annotation.Annotation;
import java.lang.reflect.Method;

public class ProceedingJoinPointUtils {

    public static String getMethodName(ProceedingJoinPoint joinPoint) {
        if (joinPoint == null)
            return "";
        return joinPoint.getSignature().getName();
    }


    public static String getSimpleClassName(ProceedingJoinPoint joinPoint) {
        if (joinPoint == null)
            return "";
        return joinPoint.getSignature().getDeclaringType().getSimpleName();
    }

    /**
     * 获取注解，优先获取方法上的注解，方法没有注解时获取类上注解
     * @param joinPoint
     * @param annotationClazz
     * @param <T>
     * @return
     */
    public static <T extends Annotation> T getAnnotation(ProceedingJoinPoint joinPoint, Class<T> annotationClazz) {
        //切点方法
        MethodSignature methodSignature = (MethodSignature) joinPoint.getSignature();
        Method method = methodSignature.getMethod();
        T annotation = method.getAnnotation(annotationClazz);
        if (annotation != null)
            return annotation;
        return joinPoint.getTarget().getClass().getAnnotation(annotationClazz);
    }

    public static <T extends Annotation> T getClassAnnotation(ProceedingJoinPoint joinPoint, Class<T> annotationClazz) {
        //切点类
        Class clazz = joinPoint.getSignature().getDeclaringType();
        //切点类上的注解
        return (T) clazz.getAnnotation(annotationClazz);
    }


    public static <T extends Annotation> T getMethodAnnotation(ProceedingJoinPoint joinPoint, Class<T> annotationClazz) {
        //切点方法
        MethodSignature methodSignature = (MethodSignature) joinPoint.getSignature();
        Method method = methodSignature.getMethod();
        return method.getAnnotation(annotationClazz);
    }

    public static Object getRequest(ProceedingJoinPoint joinPoint) {
        if (joinPoint == null)
            return "";
        Object[] arguments = joinPoint.getArgs();
        Object request = null;
        if (arguments != null && arguments.length != 0) {
            request = arguments[0];
        }
        return request;
    }

}
