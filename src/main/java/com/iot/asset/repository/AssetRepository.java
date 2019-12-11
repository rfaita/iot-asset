package com.iot.asset.repository;

import com.iot.asset.model.Asset;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface AssetRepository extends MongoRepository<Asset, String> {

    Optional<Asset> findByIdAndTenantIdAndToken(String id, String tenantId, String token);

    Optional<Asset> findByIdAndTenantId(String id, String tenantId);

    Page<Asset> findAllByTenantId(String tentantId, PageRequest of);
}
