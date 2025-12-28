package com.example.demo.dto;
public class LifecycleEventRequestDTO {
    private String eventType;
    private String eventDescription;

    public String getEventType() { return eventType; }
    public void setEventType(String eventType) { this.eventType = eventType; }
    public String getEventDescription() { return eventDescription; }
    public void setEventDescription(String eventDescription) { this.eventDescription = eventDescription; }
}