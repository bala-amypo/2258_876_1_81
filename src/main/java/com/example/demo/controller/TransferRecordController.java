// package com.example.demo.controller;

// import com.example.demo.entity.TransferRecord;
// import com.example.demo.service.TransferRecordService;
// import org.springframework.security.access.prepost.PreAuthorize;
// import org.springframework.web.bind.annotation.*;

// import java.util.List;

// @RestController
// @RequestMapping("/api/transfers")
// public class TransferRecordController {

//     private final TransferRecordService transferRecordService;

//     public TransferRecordController(TransferRecordService transferRecordService) {
//         this.transferRecordService = transferRecordService;
//     }

//     // 🔐 ADMIN ONLY
//     @PreAuthorize("hasRole('ADMIN')")
//     @PostMapping("/{assetId}")
//     public TransferRecord create(@PathVariable Long assetId,
//                                  @RequestBody TransferRecord record) {
//         return transferRecordService.createTransfer(assetId, record);
//     }

//     @GetMapping("/asset/{assetId}")
//     public List<TransferRecord> getByAsset(@PathVariable Long assetId) {
//         return transferRecordService.getTransfersForAsset(assetId);
//     }

//     @GetMapping("/{id}")
//     public TransferRecord getById(@PathVariable Long id) {
//         return transferRecordService.getTransfer(id);
//     }
// }

package com.example.demo.controller;

import com.example.demo.entity.TransferRecord;
import com.example.demo.service.TransferRecordService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/transfers")
public class TransferRecordController {
    private final TransferRecordService transferService;

    public TransferRecordController(TransferRecordService transferService) { this.transferService = transferService; }

    @PostMapping("/{assetId}")
    public ResponseEntity<TransferRecord> create(@PathVariable Long assetId, @RequestBody TransferRecord record) {
        return ResponseEntity.ok(transferService.createTransfer(assetId, record));
    }

    @GetMapping("/asset/{assetId}")
    public ResponseEntity<?> getByAsset(@PathVariable Long assetId) {
        return ResponseEntity.ok(transferService.getTransfersForAsset(assetId));
    }
}