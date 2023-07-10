package com.base.pm.service.rest;


import com.base.pm.base.exception.BaseException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Primary;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;

import java.util.HashMap;
import java.util.Map;

@RequiredArgsConstructor
@Slf4j
@Primary
@Service
public class RestTemplateCallerUtils implements RestCaller {

    private final RestTemplate restTemplate;

    @Override
    public Object callRestService(String url , HttpMethod httpMethod , Object request , Class<?> responseClass , Map<String,?> params , Map<String, String> requestHeaders) throws BaseException {
        Object response=null;
        HttpHeaders headers = new HttpHeaders();
        if (requestHeaders!=null && requestHeaders.size()>0){
            for (Map.Entry<String, String> entry : requestHeaders.entrySet()) {
                headers.add(entry.getKey(),entry.getValue());
            }
        }
        headers.setContentType(MediaType.APPLICATION_JSON);
        HttpEntity<?> entity = new HttpEntity<>(request, headers);

        try {
            ResponseEntity exchange = restTemplate.exchange(url, httpMethod, entity, responseClass, params == null ? new HashMap<>() : params);
            response = exchange.getBody();
        } catch (RestClientException e) {
            throw new BaseException(e.getMessage());
        }
        return response;
    }

}

