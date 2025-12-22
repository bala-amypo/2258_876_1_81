package com.example.demo.controller;

import com.example.demo.entity.LifecycleEvent;
import com.example.demo.service.LifecycleEventService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/lifecycle-events")
public class LifecycleEventController {

    private final LifecycleEventService lifecycleEventService;

    public LifecycleEventController(LifecycleEventService lifecycleEventService) {
        this.lifecycleEventService = lifecycleEventService;
    }

    @PostMapping("/log")
    public ResponseEntity<LifecycleEvent> logEvent(@RequestParam Long assetId,
                                                   @RequestParam Long userId,
                                                   @RequestBody LifecycleEvent event) {
        LifecycleEvent savedEvent = lifecycleEventService.logEvent(assetId, userId, event);
        return ResponseEntity.ok(savedEvent);
    }

    @GetMapping("/asset/{assetId}")
    public ResponseEntity<List<LifecycleEvent>> getEventsForAsset(@PathVariable Long assetId) {
        return ResponseEntity.ok(lifecycleEventService.getEventsForAsset(assetId));
    }

    @GetMapping("/{id}")
    public ResponseEntity<LifecycleEvent> getEvent(@PathVariable Long id) {
        return ResponseEntity.ok(lifecycleEventService.getEvent(id));
    }

}
