package com.example.demo.entity;

import jakarta.persistence.*;
import java.time.LocalDate;

@Entity
@Table(name = "transfer_records")
public class TransferRecord {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "asset_id")
    private Asset asset;

    private String fromDepartment;
    private String toDepartment;
    private LocalDate transferDate;

    @ManyToOne
    @JoinColumn(name = "approved_by")
    private User approvedBy;

    public TransferRecord() {
    }

    public TransferRecord(Asset asset, String fromDepartment,
                          String toDepartment, LocalDate transferDate,
                          User approvedBy) {
        this.asset = asset;
        this.fromDepartment = fromDepartment;
        this.toDepartment = toDepartment;
        this.transferDate = transferDate;
        this.approvedBy = approvedBy;
    }

    public Long getId() {
        return id;
    }
}
