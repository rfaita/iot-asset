package com.iot.edge.service;

import com.iot.edge.component.LocalTokenCacheComponent;
import com.iot.edge.model.Asset;
import com.iot.edge.repository.AssetRepository;
import dto.SensorData;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class AssetService {

    private final static Logger LOGGER = LoggerFactory.getLogger(AssetService.class.getName());

    private final AssetRepository repository;
    private final LocalTokenCacheComponent cache;

    public AssetService(AssetRepository repository, LocalTokenCacheComponent cache) {
        this.repository = repository;
        this.cache = cache;
    }

    public boolean validateSensorData(SensorData sensorData) {

        if (!cache.validateSensorData(
                sensorData.getId(),
                sensorData.getTenantId(),
                sensorData.getToken())) {

            LOGGER.info("Entry not found on cache: {},{},{}",
                    sensorData.getId(),
                    sensorData.getTenantId(),
                    sensorData.getToken());

            Optional<Asset> result =
                    repository.findByIdAndTenantIdAndToken(
                            sensorData.getId(),
                            sensorData.getTenantId(),
                            sensorData.getToken()
                    );

            if (result.isPresent()) {
                cache.put(sensorData.getId(),
                        sensorData.getTenantId(),
                        sensorData.getToken());

                return result.isPresent();
            } else {
                return Boolean.FALSE;
            }
        } else {
            LOGGER.info("Entry found on cache: {},{},{}",
                    sensorData.getId(),
                    sensorData.getTenantId(),
                    sensorData.getToken());
            return Boolean.TRUE;
        }

    }
}
