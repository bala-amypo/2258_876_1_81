// package com.example.demo.controller;

// import com.example.demo.entity.DisposalRecord;
// import com.example.demo.service.DisposalRecordService;
// import org.springframework.security.access.prepost.PreAuthorize;
// import org.springframework.web.bind.annotation.*;

// import java.util.List;

// @RestController
// @RequestMapping("/api/disposals")
// public class DisposalRecordController {

//     private final DisposalRecordService disposalRecordService;

//     public DisposalRecordController(DisposalRecordService disposalRecordService) {
//         this.disposalRecordService = disposalRecordService;
//     }

//     // 🔐 ADMIN ONLY
//     @PreAuthorize("hasRole('ADMIN')")
//     @PostMapping("/{assetId}")
//     public DisposalRecord create(@PathVariable Long assetId,
//                                  @RequestBody DisposalRecord record) {
//         return disposalRecordService.createDisposal(assetId, record);
//     }

//     @GetMapping
//     public List<DisposalRecord> getAll() {
//         return disposalRecordService.getAllDisposals();
//     }

//     @GetMapping("/{id}")
//     public DisposalRecord getById(@PathVariable Long id) {
//         return disposalRecordService.getDisposal(id);
//     }
// }


package com.example.demo.controller;

import com.example.demo.entity.DisposalRecord;
import com.example.demo.service.DisposalRecordService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/disposals")
public class DisposalRecordController {
    private final DisposalRecordService disposalService;
    public DisposalRecordController(DisposalRecordService disposalService) { this.disposalService = disposalService; }

    @PostMapping("/{assetId}")
    public ResponseEntity<DisposalRecord> create(@PathVariable Long assetId, @RequestBody DisposalRecord disposal) {
        return ResponseEntity.ok(disposalService.createDisposal(assetId, disposal));
    }

    @GetMapping("/{id}")
    public ResponseEntity<DisposalRecord> getById(@PathVariable Long id) {
        return ResponseEntity.ok(disposalService.getDisposal(id));
    }

    @GetMapping
    public ResponseEntity<?> getAll() { return ResponseEntity.ok(disposalService.getAllDisposals()); }
}