// package com.example.demo.controller;

// import com.example.demo.entity.LifecycleEvent;
// import com.example.demo.service.LifecycleEventService;
// import org.springframework.security.access.prepost.PreAuthorize;
// import org.springframework.web.bind.annotation.*;

// import java.util.List;

// @RestController
// @RequestMapping("/api/events")
// public class LifecycleEventController {

//     private final LifecycleEventService lifecycleEventService;

//     public LifecycleEventController(LifecycleEventService lifecycleEventService) {
//         this.lifecycleEventService = lifecycleEventService;
//     }

//     // 👥 USER + ADMIN
//     @PreAuthorize("hasAnyRole('USER','ADMIN')")
//     @PostMapping("/{assetId}/{userId}")
//     public LifecycleEvent log(@PathVariable Long assetId,
//                               @PathVariable Long userId,
//                               @RequestBody LifecycleEvent event) {
//         return lifecycleEventService.logEvent(assetId, userId, event);
//     }

//     @GetMapping("/asset/{assetId}")
//     public List<LifecycleEvent> getByAsset(@PathVariable Long assetId) {
//         return lifecycleEventService.getEventsForAsset(assetId);
//     }

//     @GetMapping("/{id}")
//     public LifecycleEvent getById(@PathVariable Long id) {
//         return lifecycleEventService.getEvent(id);
//     }
// }

package com.example.demo.controller;

import com.example.demo.entity.LifecycleEvent;
import com.example.demo.service.LifecycleEventService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/events")
public class LifecycleEventController {
    private final LifecycleEventService eventService;
    public LifecycleEventController(LifecycleEventService eventService) { this.eventService = eventService; }

    @PostMapping("/{assetId}/{userId}")
    public ResponseEntity<LifecycleEvent> logEvent(@PathVariable Long assetId, @PathVariable Long userId, @RequestBody LifecycleEvent event) {
        return ResponseEntity.ok(eventService.logEvent(assetId, userId, event));
    }

    @GetMapping("/asset/{assetId}")
    public ResponseEntity<?> getByAsset(@PathVariable Long assetId) {
        return ResponseEntity.ok(eventService.getEventsForAsset(assetId));
    }
}