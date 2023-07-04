package com.base.pm.base.dto.logging;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Timestamp;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class LogDTO {

    private String requestId;
    private String requestHttpMethod;

    private String requestUrl;

    private Object requestBody;
    private Timestamp requestStartExecution;

    private int httpStatus;

    private Object responseBody;

    private Timestamp responseEndExecution;

}
