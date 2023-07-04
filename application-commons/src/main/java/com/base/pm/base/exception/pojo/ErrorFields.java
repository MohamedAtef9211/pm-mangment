package com.base.pm.base.exception.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ErrorFields {
    private String fieldName;
    private String message;
}
