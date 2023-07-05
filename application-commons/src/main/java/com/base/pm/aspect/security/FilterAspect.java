package com.base.pm.aspect.security;

import com.base.pm.base.aspect.BaseAspect;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

@Component
@Aspect
@Slf4j
@Order(1)
public class FilterAspect extends BaseAspect {

    @Around("@annotation(org.springframework.web.bind.annotation.RequestMapping)")
    public Object authorizeRequest(ProceedingJoinPoint joinPoint ) throws Throwable {
        log.info("Inside FilterAspect");
        addRequestId();
        if(isPublicEndPoint(joinPoint)){
            log.info("Inside FilterAspect -- Public");
        }
        return joinPoint.proceed();
    }
}
