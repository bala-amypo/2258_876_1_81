package com.example.demo.controller;

import com.example.demo.dto.DisposalRequest;
import com.example.demo.entity.DisposalRecord;
import com.example.demo.entity.User;
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

    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping("/{assetId}")
    public DisposalRecord create(
            @PathVariable Long assetId,
            @RequestBody DisposalRequest request
    ) {
        
        DisposalRecord record = new DisposalRecord();
        record.setDisposalMethod(request.getDisposalMethod());
        record.setDisposalDate(request.getDisposalDate());
        record.setNotes(request.getNotes());

        User approver = new User();
        approver.setId(request.getApprovedByUserId());
        record.setApprovedBy(approver);

        return disposalRecordService.createDisposal(assetId, record);
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
