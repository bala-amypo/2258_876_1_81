package com.example.demo.controller;

import com.example.demo.dto.DisposalRequest;
import com.example.demo.entity.DisposalRecord;
import com.example.demo.service.DisposalRecordService;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/disposals")
public class DisposalRecordController {

    private final DisposalRecordService disposalRecordService;

    public DisposalRecordController(DisposalRecordService disposalRecordService) {
        this.disposalRecordService = disposalRecordService;
    }

    // 🔐 ADMIN ONLY
    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping("/{assetId}")
    public DisposalRecord create(
            @PathVariable Long assetId,
            @RequestBody DisposalRequest request
    ) {
        return disposalRecordService.createDisposal(assetId, request);
    }

    @GetMapping
    public List<DisposalRecord> getAll() {
        return disposalRecordService.getAllDisposals();
    }

    @GetMapping("/{id}")
    public DisposalRecord getById(@PathVariable Long id) {
        return disposalRecordService.getDisposal(id);
    }
}
