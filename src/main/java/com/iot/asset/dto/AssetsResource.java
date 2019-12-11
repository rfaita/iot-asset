package com.iot.asset.dto;

import com.iot.asset.controller.AssetController;
import com.iot.asset.model.Asset;
import org.springframework.data.domain.Page;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.IanaLinkRelations;
import org.springframework.hateoas.PagedModel;

import java.util.stream.Collectors;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

public class AssetsResource extends PagedModel<AssetResource> {

    public AssetsResource() {
    }

    public AssetsResource(String tenantId, Page<Asset> data) {


        super(
                data.getContent().stream().map(AssetResource::new).collect(Collectors.toList()),
                new PagedModel.PageMetadata(
                        data.getSize(), data.getNumber(), data.getTotalElements(), data.getTotalPages())
        );

        this.add(linkTo(methodOn(AssetController.class)
                .findAllByTenantId(tenantId, data.getPageable().getPageNumber(),
                        data.getTotalPages(),
                        data.getSort().toString())
        )
                .withSelfRel()
                .expand()
                .withType("GET"));

        if (data.getNumber() < data.getTotalPages()) {
            this.add(linkTo(methodOn(AssetController.class)
                    .findAllByTenantId(tenantId, data.getPageable().getPageNumber() + 1,
                            data.getPageable().getPageSize(),
                            data.getSort().toString())
            )
                    .withRel(IanaLinkRelations.NEXT)
                    .expand()
                    .withType("GET"));
        }
        if (data.getNumber() > 0) {
            this.add(linkTo(methodOn(AssetController.class)
                    .findAllByTenantId(tenantId, data.getPageable().getPageNumber() - 1,
                            data.getPageable().getPageSize(),
                            data.getSort().toString())
            )
                    .withRel(IanaLinkRelations.PREV)
                    .expand()
                    .withType("GET"));
        }
    }
}
