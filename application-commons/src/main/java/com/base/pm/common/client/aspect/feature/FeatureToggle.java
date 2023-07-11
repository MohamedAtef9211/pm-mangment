package com.base.pm.common.client.aspect.feature;

import com.base.pm.common.client.feature.MyFeature;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

@Retention(RetentionPolicy.RUNTIME)
public @interface FeatureToggle {
    MyFeature value();
}
