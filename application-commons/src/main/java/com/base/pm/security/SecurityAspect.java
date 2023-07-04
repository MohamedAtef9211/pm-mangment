package com.base.pm.security;

import com.base.pm.base.aspect.BaseAspect;
import com.base.pm.security.validator.AnyValidator;
import com.base.pm.security.validator.ValidateRequest;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Component
@Aspect
@Slf4j
@Order(2)
public class SecurityAspect extends BaseAspect {

    @Autowired
    private ApplicationContext applicationContext;

    // Authorized

    @Around("@annotation(AuthorizedEndPoint)")
    public Object authorizeRequest(ProceedingJoinPoint joinPoint ) throws Throwable {
        log.info("inside authorizeRequest - logRequestMapping");
        HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
        log.info("request.getHeader = " + request.getHeader("gateway"));
        AuthorizedEndPoint authorizedEndPoint =((MethodSignature) joinPoint.getSignature()).getMethod().getAnnotation(AuthorizedEndPoint.class);
        Class<? extends ValidateRequest>[] validatorsList = authorizedEndPoint.value();
        List<String> errorList = new ArrayList<>(validatorsList.length);
        Arrays.stream(validatorsList).forEach(validatorClass -> {
            try {
                ValidateRequest validator = applicationContext.getBean(validatorClass);
                log.info("class name : " + validatorClass.getName() + " validator.isValid()" + validator.isValid());
                if(!validator.isValid()){
                    log.info("inside !validator.isValid()");
                    errorList.add(validatorClass.getName());
                }
            } catch (Exception e){
                e.printStackTrace();
            }
        });
        if(errorList.size() > 0){
            throw new RuntimeException("User Not Authorized");
        }
        return joinPoint.proceed();
    }

    // Public
    @Around("@annotation(PublicEndPoint) && !@annotation(AuthorizedEndPoint)")
    public Object publicRequest(ProceedingJoinPoint joinPoint) throws Throwable {
        return joinPoint.proceed();
    }
}
