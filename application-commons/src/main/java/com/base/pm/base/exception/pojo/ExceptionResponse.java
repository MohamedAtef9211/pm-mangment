package com.base.pm.base.exception.pojo;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ExceptionResponse {
    private String requestId;
    private String message;
    private Object stackTrace;

    private List<ErrorFields> errorList;

    public ExceptionResponse(String requestId, String message) {
        this.requestId = requestId;
        this.message = message;
    }

    public ExceptionResponse(String requestId, String message, Object stackTrace) {
        this.requestId = requestId;
        this.message = message;
        this.stackTrace = stackTrace;
    }

    public ExceptionResponse(String requestId, String message, List<ErrorFields> errorList) {
        this.requestId = requestId;
        this.message = message;
        this.errorList = errorList;
    }
}
