package com.yf.aop;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

/**
 * @Date 2020/11/23
 * @Author yangfa
 * @Description
 */
@Aspect
@Component
public class AopTestAspect {

    @Around(value = "@annotation(aopTest)")
    public Object around(ProceedingJoinPoint joinPoint, AopTest aopTest) throws Throwable {
        System.out.println("1环绕通知");
        Object proceed = joinPoint.proceed();
        System.out.println("apoTest age is:" + aopTest.age());
        System.out.println("apoTest name is:" +  aopTest.name());
        System.out.println("2环绕通知");
        return proceed;
    }
}

//    // 切 含有这个注解的方法
//    @Around(value = "@annotation(com.yf.aop.AopTest)")
//    public Object around(final ProceedingJoinPoint pjp) throws Exception {
//        //获取类的字节码对象，通过字节码对象获取方法信息
//        Class<?> targetCls = pjp.getTarget().getClass();
//    }

//    @Before(value = "@annotation(com.yf.aop.AopTest)")
//    public void doBefore(JoinPoint joinPoint) throws Throwable {
//        System.out.println("前置通知");
//        System.out.println("joinPoint 所有的参数 = " + joinPoint.getArgs());
//        System.out.println("joinPoint.getSignature() = " + joinPoint.getSignature());
//        System.out.println("joinPoint.getSignature().toString() = " + joinPoint.getSignature().toString());
//        System.out.println("joinPoint.getSignature().toShortString() = " + joinPoint.getSignature().toShortString());
//        System.out.println("joinPoint.getSignature().getName() = " + joinPoint.getSignature().getName());
//        System.out.println("joinPoint.getSignature().getDeclaringType() = " + joinPoint.getSignature().getDeclaringType());
//        System.out.println("joinPoint.getSignature().getDeclaringTypeName() = " + joinPoint.getSignature().getDeclaringTypeName());
//
//
//    }

//    @After(value = "@annotation(com.yf.aop.AopTest)")
//    public void doAfterReturning(JoinPoint joinPoint) throws Throwable {
//        System.out.println("后置通知");
//        System.out.println("joinPoint 所有的参数 = " + joinPoint.getArgs());
//        System.out.println("joinPoint.getSignature() = " + joinPoint.getSignature());
//        System.out.println("joinPoint.getSignature().toString() = " + joinPoint.getSignature().toString());
//        System.out.println("joinPoint.getSignature().toShortString() = " + joinPoint.getSignature().toShortString());
//        System.out.println("joinPoint.getSignature().getName() = " + joinPoint.getSignature().getName());
//        System.out.println("joinPoint.getSignature().getDeclaringType() = " + joinPoint.getSignature().getDeclaringType());
//        System.out.println("joinPoint.getSignature().getDeclaringTypeName() = " + joinPoint.getSignature().getDeclaringTypeName());
//    }