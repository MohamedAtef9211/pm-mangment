package com.base.pm.aspect.security;

import com.base.pm.aspect.security.validator.ValidateRequest;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.TYPE, ElementType.METHOD})
public @interface AuthorizedEndPoint {

   Class<? extends ValidateRequest>[] value();
}
