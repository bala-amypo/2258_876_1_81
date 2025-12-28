package com.example.demo.service;

import com.example.demo.entity.DisposalRecord;
import java.util.List;

public interface DisposalRecordService {

    // ✅ REQUIRED BY TEST CASES
    DisposalRecord createDisposal(Long assetId, DisposalRecord record);

    DisposalRecord getDisposal(Long id);

    List<DisposalRecord> getAllDisposals();
}
