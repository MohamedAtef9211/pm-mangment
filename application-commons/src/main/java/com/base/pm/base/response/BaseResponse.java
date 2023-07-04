package com.base.pm.base.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.http.HttpStatus;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class BaseResponse<T> {
    private Boolean status;
    private HttpStatus httpStatus;
    private  T result ;
}
