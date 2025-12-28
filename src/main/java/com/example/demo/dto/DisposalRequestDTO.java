package com.example.demo.dto;
import java.time.LocalDate;
public class DisposalRequestDTO {
    private String disposalMethod;
    private LocalDate disposalDate;
    private Long approvedByUserId;
    private String notes;

    public String getDisposalMethod() { return disposalMethod; }
    public LocalDate getDisposalDate() { return disposalDate; }
    public String getNotes() { return notes; }
    public Long getApprovedByUserId() { return approvedByUserId; }
    // Setters
    public void setDisposalMethod(String m) { this.disposalMethod = m; }
    public void setDisposalDate(LocalDate d) { this.disposalDate = d; }
    public void setNotes(String n) { this.notes = n; }
    public void setApprovedByUserId(Long id) { this.approvedByUserId = id; }
}