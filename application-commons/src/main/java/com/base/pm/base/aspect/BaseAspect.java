package com.base.pm.base.aspect;

import com.base.pm.aspect.security.PublicEndPoint;
import com.base.pm.base.dto.logging.LogDTO;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import java.util.UUID;

public class BaseAspect {
    protected ObjectMapper mapper = new ObjectMapper();

    protected static String addRequestId() {
        HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
        String requestId = UUID.randomUUID().toString();
        request.setAttribute("requestUUID", requestId);
        return requestId;
    }

    protected static String getRequestId() {
        HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
        String requestId = (String) request.getAttribute("requestUUID");
        return requestId;
    }


    protected void log(LogDTO logDTO) {
        // Perform the logging operation here
        System.out.println(logDTO.toString()); // Example: Printing the log entry to the console
    }

    protected boolean isPublicEndPoint(ProceedingJoinPoint joinPoint){
        return ((MethodSignature) joinPoint.getSignature()).getMethod().isAnnotationPresent(PublicEndPoint.class);
    }

}
