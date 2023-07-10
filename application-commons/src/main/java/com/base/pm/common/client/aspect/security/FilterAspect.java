package com.base.pm.common.client.aspect.security;

import com.base.pm.base.exception.BaseException;
import com.base.pm.common.client.aspect.security.validator.ValidateRequest;
import com.base.pm.common.client.base.aspect.BaseAspect;
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
        if(isPublicEndPoint(joinPoint)){
            log.info("Inside FilterAspect -- Public");
            return joinPoint.proceed();
        }

//        HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
//        final String authHeader = request.getHeader("Authorization");
//
//        if (authHeader == null ||!authHeader.startsWith("Bearer ")) {
//            throw new BaseException("User is not authorized");
//        }
//        final String jwt = authHeader.substring(7);
//        System.out.println("jwt = " + jwt);

        if(isAuthorizedWithRoles(joinPoint)){
            log.info("Inside FilterAspect -- isAuthorizedWithRoles");
            List checkedRoles = checkRoles(joinPoint);
            if(checkedRoles.size() > 0){
                throw new BaseException("User is not doesnt have roles " + checkedRoles.get(0));
            }
            return joinPoint.proceed();
        }
        // validate Request
        return joinPoint.proceed();

    }

    private List checkRoles(ProceedingJoinPoint joinPoint){
        AuthorizedWithRoles authorizedEndPoint =((MethodSignature) joinPoint.getSignature()).getMethod().getAnnotation(AuthorizedWithRoles.class);
        ApplicationRoles[] value = authorizedEndPoint.value();
        List<String> errorList = new ArrayList<>(value.length);
        Arrays.stream(value).forEach(applicationRole -> {
            if(!isUserInRole(applicationRole)) {
                errorList.add(applicationRole.name());
            }
        });
        return errorList;
    }

    private boolean isUserInRole(ApplicationRoles role){
        log.info(role.name());
        return true;
    }
}
