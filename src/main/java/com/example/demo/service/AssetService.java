package com.example.demo.service;

import java.util.List;
import com.example.demo.entity.Asset;

public interface AssetService {

    Asset createAsset(Asset asset);

    Asset getAsset(Long id);

    List<Asset> getAllAssets();

    Asset updateStatus(Long assetId, String status);
}
