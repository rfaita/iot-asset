package com.iot.edge;

import com.iot.edge.model.Asset;
import com.iot.edge.repository.AssetRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class EdgeApplication implements CommandLineRunner {

    private final AssetRepository repository;

    public EdgeApplication(AssetRepository repository) {
        this.repository = repository;
    }

    public static void main(String[] args) {
        SpringApplication.run(EdgeApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
        Asset asset = new Asset();
        asset.setId("1");
        asset.setTenantId("1");
        asset.setToken("123");

        this.repository.save(asset);
    }
}
