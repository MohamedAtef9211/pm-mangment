package com.base.pm.aspect.security;

import com.base.pm.base.aspect.BaseAspect;
import com.base.pm.aspect.security.validator.ValidateRequest;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.util.MimeType;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Component
@Aspect
@Slf4j
@Order(3)
public class SecurityAspect extends BaseAspect {

    @Autowired
    private ApplicationContext applicationContext;

    // Authorized

    @Around("@annotation(com.base.pm.aspect.security.AuthorizedEndPoint)")
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
            //throw new RuntimeException("User Not Authorized");
            return new ResponseEntity<>(errorList.get(0), HttpStatus.BAD_REQUEST);
        }
        return joinPoint.proceed();
    }

    // Public
    @Around("@annotation(com.base.pm.aspect.security.PublicEndPoint) && !@annotation(com.base.pm.aspect.security.AuthorizedEndPoint)")
    public Object publicRequest(ProceedingJoinPoint joinPoint) throws Throwable {
        return joinPoint.proceed();
    }
}