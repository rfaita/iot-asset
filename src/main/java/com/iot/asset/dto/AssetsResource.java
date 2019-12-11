package com.iot.asset.dto;

import com.iot.asset.controller.AssetController;
import com.iot.asset.model.Asset;
import org.springframework.data.domain.Page;
import org.springframework.hateoas.IanaLinkRelations;
import org.springframework.hateoas.LinkRelation;
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

        addSelfLink(tenantId, data);

        if (data.getNumber() < data.getTotalPages() - 1) {
            addNavegationLink(tenantId, data, data.getPageable().getPageNumber() + 1, IanaLinkRelations.NEXT);
        }
        if (data.getNumber() > 0) {
            addNavegationLink(tenantId, data, data.getPageable().getPageNumber() - 1, IanaLinkRelations.PREV);
        }
    }

    private void addSelfLink(String tenantId, Page<Asset> data) {
        this.add(linkTo(methodOn(AssetController.class)
                .findAllByTenantId(tenantId, data.getPageable().getPageNumber(),
                        data.getTotalPages(),
                        data.getSort().toString())
        )
                .withSelfRel()
                .expand()
                .withType("GET"));
    }

    private void addNavegationLink(String tenantId, Page<Asset> data, int page, LinkRelation prev) {
        this.add(linkTo(methodOn(AssetController.class)
                .findAllByTenantId(tenantId, page,
                        data.getPageable().getPageSize(),
                        data.getSort().toString())
        )
                .withRel(prev)
                .expand()
                .withType("GET"));
    }
}
