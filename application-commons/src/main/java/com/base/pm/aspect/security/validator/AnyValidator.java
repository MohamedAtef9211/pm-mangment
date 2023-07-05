package com.base.pm.aspect.security.validator;

import org.springframework.stereotype.Component;

@Component
public class AnyValidator extends ValidateRequest{
    @Override
    public boolean isValid() {
        return true;
    }
}
