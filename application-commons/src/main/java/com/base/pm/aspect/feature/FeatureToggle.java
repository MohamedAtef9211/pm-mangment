package com.base.pm.aspect.feature;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

@Retention(RetentionPolicy.RUNTIME)
public @interface FeatureToggle {
    MyFeature value();
}
