package com.base.pm.service.rest;

import org.springframework.http.HttpMethod;

import java.util.Map;

public interface RestCaller {
    Object callRestService(String url , HttpMethod httpMethod , Object request , Class<?> responseClass , Map<String,?> params , Map<String, String> requestHeaders) throws RuntimeException;
}
