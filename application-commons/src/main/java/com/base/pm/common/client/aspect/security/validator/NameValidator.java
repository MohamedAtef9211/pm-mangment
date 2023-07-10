package com.base.pm.common.client.aspect.security.validator;

import org.springframework.stereotype.Component;

@Component
public class NameValidator extends ValidateRequest{
    @Override
    public boolean isValid() {
        return true;
    }
}
