package com.othmane.SpringBoot.tutorial.config;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.Comment;
import org.springframework.boot.actuate.endpoint.annotation.Endpoint;
import org.springframework.boot.actuate.endpoint.annotation.ReadOperation;
import org.springframework.boot.actuate.endpoint.annotation.Selector;
import org.springframework.stereotype.Component;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

@Component
@Endpoint(id = "features")
public class FeatureEndpoint {
    private Map<String,Feature> FeatureMap = new ConcurrentHashMap<>();

    public FeatureEndpoint(){
        FeatureMap.put("Department",new Feature(true));
        FeatureMap.put("User",new Feature(false));
    }

    @ReadOperation
    public Map<String,Feature> features(){
        return FeatureMap;
    }

    @ReadOperation
    public Feature features(@Selector String featureName){
        return FeatureMap.get(featureName);
    }

    @Data
    @AllArgsConstructor
    @NoArgsConstructor
    private static class Feature{
        private boolean isEnabled;
    }
}
