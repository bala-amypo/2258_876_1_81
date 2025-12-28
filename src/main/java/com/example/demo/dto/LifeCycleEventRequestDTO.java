package com.example.demo.dto;
public class LifecycleEventRequestDTO {
    private String eventType;
    private String eventDescription;
    public String getEventType() { return eventType; }
    public void setEventType(String et) { this.eventType = et; }
    public String getEventDescription() { return eventDescription; }
    public void setEventDescription(String ed) { this.eventDescription = ed; }
}