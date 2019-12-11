package com.iot.asset.service;

import com.iot.asset.model.Asset;
import com.iot.asset.repository.AssetRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.UUID;

@Service
public class AssetService {

    private final static Logger LOGGER = LoggerFactory.getLogger(AssetService.class.getName());

    private final AssetRepository repository;

    public AssetService(AssetRepository repository) {
        this.repository = repository;
    }


    public Page<Asset> findAllByTenantId(String tentantId, PageRequest of) {
        return this.repository.findAllByTenantId(tentantId, of);
    }

    public Asset save(Asset asset) {

        if (StringUtils.isEmpty(asset.getToken())) {
            asset.setToken(UUID.randomUUID().toString());
        }

        return this.repository.save(asset);

    }

    public Asset findByIdAndTenantId(String id, String tenantId) {
        return this.repository.findByIdAndTenantId(id, tenantId).orElse(null);
    }
}
