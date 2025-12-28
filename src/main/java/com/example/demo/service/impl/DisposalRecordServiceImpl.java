package com.example.demo.service.impl;

import com.example.demo.dto.DisposalRequest;
import com.example.demo.entity.Asset;
import com.example.demo.entity.DisposalRecord;
import com.example.demo.entity.User;
import com.example.demo.exception.ResourceNotFoundException;
import com.example.demo.exception.ValidationException;
import com.example.demo.repository.AssetRepository;
import com.example.demo.repository.DisposalRecordRepository;
import com.example.demo.repository.UserRepository;
import com.example.demo.service.DisposalRecordService;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@Service
public class DisposalRecordServiceImpl implements DisposalRecordService {

    private final DisposalRecordRepository disposalRecordRepository;
    private final AssetRepository assetRepository;
    private final UserRepository userRepository;

    public DisposalRecordServiceImpl(
            DisposalRecordRepository disposalRecordRepository,
            AssetRepository assetRepository,
            UserRepository userRepository) {

        this.disposalRecordRepository = disposalRecordRepository;
        this.assetRepository = assetRepository;
        this.userRepository = userRepository;
    }

    // ============================================================
    // 🔥 METHOD REQUIRED BY TEST CASES (ENTITY-BASED)
    // ============================================================
    @Override
    public DisposalRecord createDisposal(Long assetId, DisposalRecord record) {

        if (record.getApprovedBy() == null || record.getApprovedBy().getId() == null) {
            throw new ValidationException("Approver must be provided");
        }

        DisposalRequest request = new DisposalRequest();
        request.setDisposalMethod(record.getDisposalMethod());
        request.setDisposalDate(record.getDisposalDate());
        request.setNotes(record.getNotes());
        request.setApprovedByUserId(record.getApprovedBy().getId());

        return createDisposal(assetId, request);
    }

    // ============================================================
    // ✅ DTO-BASED METHOD (USED BY CONTROLLER)
    // ============================================================
    public DisposalRecord createDisposal(Long assetId, DisposalRequest request) {

        if (request.getApprovedByUserId() == null) {
            throw new ValidationException("Approver must be provided");
        }

        Asset asset = assetRepository.findById(assetId)
                .orElseThrow(() -> new ResourceNotFoundException("Asset not found"));

        User approver = userRepository.findById(request.getApprovedByUserId())
                .orElseThrow(() -> new ResourceNotFoundException("User not found"));

        if (!"ADMIN".equalsIgnoreCase(approver.getRole())) {
            throw new ValidationException("Approver must be ADMIN");
        }

        if (request.getDisposalDate() != null &&
                request.getDisposalDate().isAfter(LocalDate.now())) {
            throw new ValidationException("Disposal date cannot be in the future");
        }

        DisposalRecord disposal = new DisposalRecord();
        disposal.setAsset(asset);
        disposal.setDisposalMethod(request.getDisposalMethod());
        disposal.setDisposalDate(request.getDisposalDate());
        disposal.setNotes(request.getNotes());
        disposal.setApprovedBy(approver);
        disposal.setCreatedAt(LocalDateTime.now());

        DisposalRecord saved = disposalRecordRepository.save(disposal);

        asset.setStatus("DISPOSED");
        assetRepository.save(asset);

        return saved;
    }

    // ============================================================
    // READ METHODS
    // ============================================================
    @Override
    public DisposalRecord getDisposal(Long id) {
        return disposalRecordRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Disposal record not found"));
    }

    @Override
    public List<DisposalRecord> getAllDisposals() {
        return disposalRecordRepository.findAll();
    }
}
