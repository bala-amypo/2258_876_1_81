package com.example.demo.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;

import java.time.LocalDate;

@Entity
@Table(name = "transfer_records")
public class TransferRecord {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "asset_id", nullable = false)
    @JsonBackReference
    private Asset asset;

    private String fromDepartment;
    private String toDepartment;
    private LocalDate transferDate;

    @ManyToOne
    @JoinColumn(name = "approved_by", nullable = false)
    @JsonBackReference
    private User approvedBy;

    public TransferRecord() {
    }

    public TransferRecord(Long id, Asset asset,
                          String fromDepartment, String toDepartment,
                          LocalDate transferDate, User approvedBy) {
        this.id = id;
        this.asset = asset;
        this.fromDepartment = fromDepartment;
        this.toDepartment = toDepartment;
        this.transferDate = transferDate;
        this.approvedBy = approvedBy;
    }

    public LocalDate getTransferDate() {
        return transferDate;
    }

    public User getApprovedBy() {
        return approvedBy;
    }
}
