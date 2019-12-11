package com.iot.asset.controller;

import com.iot.asset.dto.AssetDto;
import com.iot.asset.dto.AssetResource;
import com.iot.asset.dto.AssetsResource;
import com.iot.asset.model.Asset;
import com.iot.asset.service.AssetService;
import org.springframework.core.convert.ConversionService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.http.MediaType;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("asset")
public class AssetController {

    public static final String X_TENANT_ID = "X-TenantId";
    private final AssetService service;
    private final ConversionService conversionService;

    public AssetController(AssetService service, ConversionService conversionService) {
        this.service = service;
        this.conversionService = conversionService;
    }

    @GetMapping("/{id}")
    public AssetResource findById(@RequestHeader(X_TENANT_ID) String tenantId,
                                  @PathVariable String id) {

        Asset data = service.findByIdAndTenantId(id, tenantId);

        return new AssetResource(data);


    }

    @GetMapping("/")
    public AssetsResource findAllByTenantId(@RequestHeader(X_TENANT_ID) String tenantId,
                                            @RequestParam int page,
                                            @RequestParam int size,
                                            @RequestParam(required = false) String sort) {

        Page<Asset> data = service.findAllByTenantId(tenantId,
                PageRequest.of(page, size, Sort.by(StringUtils.isEmpty(sort) ? "id" : sort))
        );

        return new AssetsResource(tenantId, data);

    }

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public AssetResource save(@RequestHeader(X_TENANT_ID) String tenantId,
                              @RequestBody AssetDto assetDto) {

        Asset asset = this.conversionService.convert(assetDto, Asset.class);

        asset.setTenantId(tenantId);

        return new AssetResource(service.save(asset));

    }

}
