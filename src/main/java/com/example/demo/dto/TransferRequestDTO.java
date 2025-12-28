package com.example.demo.dto;
import java.time.LocalDate;
public class TransferRequestDTO {
    private String fromDepartment;
    private String toDepartment;
    private LocalDate transferDate;
    private Long approvedByUserId;

    public String getFromDepartment() { return fromDepartment; }
    public String getToDepartment() { return toDepartment; }
    public LocalDate getTransferDate() { return transferDate; }
    public Long getApprovedByUserId() { return approvedByUserId; }
    // Setters
    public void setFromDepartment(String fromDept) { this.fromDepartment = fromDept; }
    public void setToDepartment(String toDept) { this.toDepartment = toDept; }
    public void setTransferDate(LocalDate date) { this.transferDate = date; }
    public void setApprovedByUserId(Long id) { this.approvedByUserId = id; }
}