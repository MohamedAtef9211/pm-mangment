package com.base.pm.base.controller;

import com.base.pm.base.response.BaseResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

public class BaseController {

    public ResponseEntity responseOk(Object data){
        return ResponseEntity.ok(new BaseResponse(Boolean.TRUE, HttpStatus.OK,data));
    }
}
