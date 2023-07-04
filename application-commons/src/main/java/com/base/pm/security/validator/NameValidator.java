package com.base.pm.security.validator;

import org.springframework.stereotype.Component;

@Component
public class NameValidator extends ValidateRequest{
    @Override
    public boolean isValid() {
        return false;
    }
}
