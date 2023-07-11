package com.base.pm.common.client.feature;

import com.base.pm.common.client.feature.EnumFeatureWrapper;
import com.base.pm.common.client.feature.MyFeature;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.togglz.core.manager.FeatureManager;
import org.togglz.core.repository.FeatureState;

@Service
@RequiredArgsConstructor
public class FeatureServices {

    private final FeatureManager featureManager;

    public boolean isFeatureActive(MyFeature myFeature){
        return featureManager.isActive(new EnumFeatureWrapper(myFeature));
    }

    public void enableFeature(MyFeature myFeature){
        if(isFeatureActive(myFeature))
            return;

        updateFeature(myFeature,true);

    }

    public void disableFeature(MyFeature myFeature){
        if(!isFeatureActive(myFeature))
            return;

        updateFeature(myFeature,false);

    }

    private void updateFeature(MyFeature myFeature,boolean status) {
        featureManager.setFeatureState(new FeatureState(new EnumFeatureWrapper(myFeature), status));
    }
}
