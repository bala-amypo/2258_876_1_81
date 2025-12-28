// package com.example.demo.controller;

// import com.example.demo.entity.Asset;
// import com.example.demo.service.AssetService;
// import org.springframework.web.bind.annotation.*;

// import java.util.List;

// @RestController
// @RequestMapping("/api/assets")
// public class AssetController {

//     private final AssetService assetService;

//     public AssetController(AssetService assetService) {
//         this.assetService = assetService;
//     }

//     @PostMapping
//     public Asset create(@RequestBody Asset asset) {
//         return assetService.createAsset(asset);
//     }

//     @GetMapping
//     public List<Asset> getAll() {
//         return assetService.getAllAssets();
//     }

//     @GetMapping("/{id}")
//     public Asset getById(@PathVariable Long id) {
//         return assetService.getAsset(id);
//     }

//     @PutMapping("/status/{id}")
//     public Asset updateStatus(@PathVariable Long id,
//                               @RequestParam String status) {
//         return assetService.updateStatus(id, status);
//     }
// }

package com.example.demo.controller;

import com.example.demo.entity.Asset;
import com.example.demo.service.AssetService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/assets")
public class AssetController {
    private final AssetService assetService;

    public AssetController(AssetService assetService) { this.assetService = assetService; }

    @PostMapping
    public ResponseEntity<Asset> create(@RequestBody Asset asset) { return ResponseEntity.ok(assetService.createAsset(asset)); }

    @GetMapping
    public ResponseEntity<List<Asset>> getAll() { return ResponseEntity.ok(assetService.getAllAssets()); }

    @GetMapping("/{id}")
    public ResponseEntity<Asset> getById(@PathVariable Long id) { return ResponseEntity.ok(assetService.getAsset(id)); }

    @PutMapping("/status/{id}")
    public ResponseEntity<Asset> updateStatus(@PathVariable Long id, @RequestParam String status) {
        return ResponseEntity.ok(assetService.updateStatus(id, status));
    }
}