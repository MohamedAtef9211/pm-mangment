 package com.base.pm.common.client.feature;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

 @RestController
@RequestMapping("/feature-togglz")
@RequiredArgsConstructor
public class FeatureController {
    private final FeatureServices featureServices;

    @RequestMapping(path = "/enable/{feature}" , method = RequestMethod.PUT)
    public void enableFeature(@PathVariable MyFeature feature){
        featureServices.enableFeature(feature);
    }

     @RequestMapping(path = "/disable/{feature}" , method = {RequestMethod.PUT})
     public void disableFeature(@PathVariable MyFeature feature){
         featureServices.disableFeature(feature);
     }
}
