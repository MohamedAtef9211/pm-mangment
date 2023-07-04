package com.base.pm.security;

import com.base.pm.base.aspect.BaseAspect;
import com.base.pm.security.validator.ValidateRequest;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Component
@Aspect
@Slf4j
@Order(1)
public class FilterAspect extends BaseAspect {

    @Around("@annotation(org.springframework.web.bind.annotation.RequestMapping)")
    public Object authorizeRequest(ProceedingJoinPoint joinPoint ) throws Throwable {
        log.info("Inside FilterAspect");
        addRequestId();
        return joinPoint.proceed();
    }
}
