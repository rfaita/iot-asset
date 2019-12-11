package com.iot.asset.converter;

import com.iot.asset.dto.AssetDto;
import com.iot.asset.model.Asset;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Component
public class AssetDtoToAssetConverter implements Converter<AssetDto, Asset> {

    @Override
    public Asset convert(AssetDto assetDto) {
        Asset asset = new Asset();
        asset.setDescription(assetDto.getDescription());
        asset.setPosition(assetDto.getPosition());
        asset.setName(assetDto.getName());
        return asset;
    }
}
