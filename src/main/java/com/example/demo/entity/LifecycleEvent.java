package com.example.demo.entity;

import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "lifecycle_events")
public class LifecycleEvent {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "asset_id")
    private Asset asset;

    private String eventType;
    private String eventDescription;
    private LocalDateTime eventDate;

    @ManyToOne
    @JoinColumn(name = "performed_by")
    private User performedBy;

    public LifecycleEvent() {
    }

    public LifecycleEvent(Asset asset, String eventType,
                          String eventDescription, User performedBy) {
        this.asset = asset;
        this.eventType = eventType;
        this.eventDescription = eventDescription;
        this.performedBy = performedBy;
    }

    @PrePersist
    public void prePersist() {
        if (this.eventDate == null) {
            this.eventDate = LocalDateTime.now();
        }
    }

    public Long getId() {
        return id;
    }

    public Asset getAsset() {
        return asset;
    }

    public void setAsset(Asset asset) {
        this.asset = asset;
    }
}
