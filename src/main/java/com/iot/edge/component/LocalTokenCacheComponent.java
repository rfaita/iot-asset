package com.iot.edge.component;

import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

@Component
public class LocalTokenCacheComponent {

    private final Map<String, Boolean> cache = new HashMap<>();

    public void put(String id, String tenantId, String token) {
        this.cache.put(id +tenantId+token, Boolean.TRUE);
    }

    public Boolean validateSensorData(String id, String tenantId, String token) {
        return this.cache.getOrDefault(id +tenantId+token, Boolean.FALSE);
    }

}
