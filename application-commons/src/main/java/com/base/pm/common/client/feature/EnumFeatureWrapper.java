package com.base.pm.common.client.feature;

import org.togglz.core.Feature;

public class EnumFeatureWrapper implements Feature {
    private Enum<?> enumValue;

    public EnumFeatureWrapper(Enum<?> enumValue) {
        this.enumValue = enumValue;
    }

    @Override
    public String name() {
        return enumValue.name();
    }
}
