package com.base.pm.common.client.aspect.feature;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;
import org.togglz.core.context.FeatureContext;

@Component
@Aspect
@Slf4j
@Order(2)
public class FeatureAspect {

    @Around("@within(featureToggle)")
    public Object execute(final ProceedingJoinPoint pjp, FeatureToggle featureToggle) throws Throwable {
        System.err.println("inside FeatureAspect");
        if (FeatureContext.getFeatureManager().isActive(new EnumFeatureWrapper(featureToggle.value()))) {
            return pjp.proceed();
        } else {
            throw new RuntimeException("This feature is currently disabled");
        }
    }
}
