package com.base.pm.exception;


import com.base.pm.base.exception.BaseException;
import com.base.pm.base.exception.pojo.ErrorFields;
import com.base.pm.base.exception.pojo.ExceptionResponse;
import com.base.pm.base.response.BaseResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import javax.servlet.http.HttpServletRequest;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.util.ArrayList;
import java.util.List;

@ControllerAdvice
@Slf4j
public class GlobalExceptionHandler {



    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<BaseResponse> handleValidationExceptions(MethodArgumentNotValidException ex, HttpServletRequest request) {
        List<ErrorFields> errorList = new ArrayList<>();
        String requestUUID = (String) request.getAttribute("requestUUID");
        StringBuilder errorString = new StringBuilder();
        ex.getBindingResult().getAllErrors().forEach((error) -> {
            String fieldName = ((FieldError) error).getField();
            String errorMessage = error.getDefaultMessage();
            errorList.add(new ErrorFields(fieldName,errorMessage));
            errorString.append(fieldName).append(": ").append(errorMessage).append(", ");
        });
        String errorStringTrimmed = errorString.length() > 0 ? errorString.substring(0, errorString.length() - 2) : "";
        request.setAttribute("exceptionResponse",new ExceptionResponse(requestUUID,"Validation error",errorStringTrimmed));
        return new ResponseEntity<>
                (new BaseResponse<>(Boolean.FALSE,HttpStatus.BAD_REQUEST,
                        new ExceptionResponse(requestUUID,"Validation error",errorList)),HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(BaseException.class)
    public ResponseEntity<BaseResponse> handleBusinessExceptions(BaseException e , HttpServletRequest request) {
        String requestUUID = (String) request.getAttribute("requestUUID");
        log.error("Error in Request {}", requestUUID);
        request.setAttribute("exceptionResponse",new ExceptionResponse(requestUUID,e.getMessage(), getStackTraceStringValue(e)));
        return new ResponseEntity<>
                (new BaseResponse<>(Boolean.FALSE,HttpStatus.BAD_GATEWAY,
                        new ExceptionResponse(requestUUID,e.getMessage())),HttpStatus.BAD_GATEWAY);
    }
    @ExceptionHandler({Exception.class,RuntimeException.class})
    public ResponseEntity<BaseResponse> handleOtherExceptions(Exception e , HttpServletRequest request) {
        String requestUUID = (String) request.getAttribute("requestUUID");
        log.error("Error in Request {}", requestUUID);
        request.setAttribute("exceptionResponse",new ExceptionResponse(requestUUID,e.getMessage(), getStackTraceStringValue(e)));
        return new ResponseEntity<>
                (new BaseResponse<>(Boolean.FALSE,HttpStatus.SERVICE_UNAVAILABLE,
                        new ExceptionResponse(requestUUID,e.getMessage())),HttpStatus.SERVICE_UNAVAILABLE);
    }

    private String getStackTraceStringValue(Exception e){
        StringWriter sw = new StringWriter();
        PrintWriter pw = new PrintWriter(sw);
        e.printStackTrace(pw);
        String stackTrace = sw.toString();
        return stackTrace;
    }
}
