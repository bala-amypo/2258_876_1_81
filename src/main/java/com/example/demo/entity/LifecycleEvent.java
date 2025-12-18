package com.example.demo.entity;
import jakarta.persistence.*;
public class LifecycleEvent{
    @Id
    private Lond id;
    private Asset asset;
    private String eventType;
    private String eventDescription;
    private LocalDateTime eventDate;
    private User performedBy;
    
}