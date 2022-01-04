package com.yf.aop;

import com.alibaba.fastjson.JSON;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;

@Aspect
@Slf4j
@Component
public class AutoLogAOP {

    @Around("@annotation(autoLog)")
    public void printLog(ProceedingJoinPoint point, AutoLog autoLog) throws Throwable {
        long start = System.currentTimeMillis();
        log.info("==============日志切面被触发=================");
        ServletRequestAttributes requestAttributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        HttpServletRequest request = requestAttributes.getRequest();
        //uri
        String requestURI = request.getRequestURI();
        //请求方式
        String method = request.getMethod();
        //url
        String url = request.getRequestURL().toString();
        //地址
        String remoteAddr = request.getRemoteAddr();
        //请求方法
        Method requestMethod = ((MethodSignature) point.getSignature()).getMethod();
        log.info("URI:{},method:{},url:{},地址:{},请求的方法:{}", requestURI, method, url, remoteAddr, requestMethod);
        //获取请求参数
        Map<String, Object> requestParams = getRequestParams(point);
        //执行调用的方法，获取结果，用这一步来控制是方法前执行还是方法后执行
        Object result = point.proceed();
        //耗时
        long time = System.currentTimeMillis() - start;
        log.info("请求参数：{}，结果：{}，耗时{}", JSON.toJSONString(requestParams), JSON.toJSONString(request), time);
    }

    private Map<String, Object> getRequestParams(ProceedingJoinPoint proceedingJoinPoint) {
        Map<String, Object> requestParams = new HashMap<>();
        //参数名
        String[] paramNames = ((MethodSignature) proceedingJoinPoint.getSignature()).getParameterNames();
        //参数值
        Object[] paramValues = proceedingJoinPoint.getArgs();
        for (int i = 0; i < paramNames.length; i++) {
            Object value = paramValues[i];
            //如果是文件对象
            if (value instanceof MultipartFile) {
                MultipartFile file = (MultipartFile) value;
                value = file.getOriginalFilename(); //获取文件名
            }
            requestParams.put(paramNames[i], value);
        }
        return requestParams;
    }
}