package com.example.demo.service.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.example.demo.entity.Asset;
import com.example.demo.repository.AssetRepository;
import com.example.demo.service.AssetService;

@Service
public class AssetServiceImpl implements AssetService {

    private final AssetRepository assetRepository;

    public AssetServiceImpl(AssetRepository assetRepository) {
        this.assetRepository = assetRepository;
    }

    @Override
    public Asset createAsset(Asset asset) {
        return assetRepository.save(asset);
    }

    @Override
    public Asset getAsset(Long id) {
        return assetRepository.findById(id).orElse(null);
    }

    @Override
    public List<Asset> getAllAssets() {
        return assetRepository.findAll();
    }

    @Override
    public Asset updateStatus(Long assetId, String status) {
        Optional<Asset> assetOpt = assetRepository.findById(assetId);
        if (assetOpt.isPresent()) {
            Asset asset = assetOpt.get();
            asset.setStatus(status);
            return assetRepository.save(asset);
        }
        return null;
    }
}
