package com.iot.edge.repository;

import com.iot.edge.model.Asset;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface AssetRepository extends MongoRepository<Asset, String> {

    Optional<Asset> findByIdAndTenantIdAndToken(String id, String tenantId, String token);

}
