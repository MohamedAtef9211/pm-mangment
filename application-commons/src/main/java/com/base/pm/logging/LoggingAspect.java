package com.base.pm.logging;

import com.base.pm.base.aspect.BaseAspect;
import com.base.pm.base.dto.logging.LogDTO;
import com.fasterxml.jackson.core.JsonProcessingException;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import java.sql.Timestamp;

@Component
@Aspect
@Slf4j
@Order(Ordered.LOWEST_PRECEDENCE)
public class LoggingAspect extends BaseAspect {

    @Around("@annotation(org.springframework.web.bind.annotation.RequestMapping) && !@annotation(SkipLogging)")
    public Object logRequestMapping(ProceedingJoinPoint joinPoint) throws Throwable {
        log.info("inside LoggingAspect - logRequestMapping");

        HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();

        LogDTO logDTO = new LogDTO();
        logDTO.setRequestId(getRequestId());
        logDTO.setRequestHttpMethod(request.getMethod());
        logDTO.setRequestUrl(request.getRequestURI());
        Object[] args = joinPoint.getArgs();
        logDTO.setRequestBody(mapper.writeValueAsString(args)); // Modify this to capture the request body if needed
        logDTO.setRequestStartExecution(new Timestamp(System.currentTimeMillis()));

        request.setAttribute("logRequestMapping",logDTO);

        Object result = joinPoint.proceed();
        logDTO.setHttpStatus(HttpStatus.OK.value()); // Modify this to capture the actual response status
        logDTO.setResponseBody(mapper.writeValueAsString(result)); // Modify this to capture the response body if needed
        logDTO.setResponseEndExecution(new Timestamp(System.currentTimeMillis()));

        log(logDTO);
        return result;
    }

    // Log Exceptions
    @AfterReturning(pointcut = "@annotation(exceptionHandler))", returning = "returnValue")
    public void logHandledException(JoinPoint joinPoint, ExceptionHandler exceptionHandler, Object returnValue) throws JsonProcessingException {
        log.info("inside logHandledException");

        HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
        if(request.getAttribute("logRequestMapping") != null) {
            LogDTO logDTO = (LogDTO) request.getAttribute("logRequestMapping");
            int status = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getResponse().getStatus();
            log.info("status = " + status);
            logDTO.setHttpStatus(status); // Modify this to capture the actual response status
            logDTO.setResponseBody(mapper.writeValueAsString(returnValue)); // Modify this to capture the response body if needed
            logDTO.setResponseEndExecution(new Timestamp(System.currentTimeMillis()));
            log(logDTO);
        }
    }


}
