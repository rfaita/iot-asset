package com.iot.asset.dto;

import com.iot.asset.controller.AssetController;
import com.iot.asset.model.Asset;
import org.springframework.hateoas.EntityModel;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

public class AssetResource extends EntityModel<Asset> {

    public AssetResource() {
    }

    public AssetResource(Asset content) {
        super(content,
                linkTo(methodOn(AssetController.class).findById(content.getTenantId(), content.getId()))
                        .withSelfRel()
                        .withType("GET"));
    }
}
