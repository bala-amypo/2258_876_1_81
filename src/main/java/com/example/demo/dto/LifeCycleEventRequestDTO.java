package com.example.demo.dto;

import lombok.Data;

@Data
public class LifecycleEventRequestDTO {
    private String eventType;
    private String eventDescription;
}