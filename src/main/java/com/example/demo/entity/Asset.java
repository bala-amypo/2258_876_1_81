package com.example.demo.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import java.util.List;

@Entity
@Table(name = "assets")
public class Asset {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true)
    private String assetTag;

    private String assetType;
    private String status;

    @ManyToOne
    @JoinColumn(name = "current_holder_id")
    @JsonBackReference
    private User currentHolder;

    @OneToMany(mappedBy = "asset", cascade = CascadeType.ALL)
    @JsonManagedReference
    private List<LifecycleEvent> lifecycleEvents;

    @OneToMany(mappedBy = "asset", cascade = CascadeType.ALL)
    @JsonManagedReference
    private List<TransferRecord> transferRecords;

    @OneToOne(mappedBy = "asset", cascade = CascadeType.ALL)
    @JsonManagedReference
    private DisposalRecord disposalRecord;

    public Asset() {
    }

    public Asset(String assetTag, String assetType, String status) {
        this.assetTag = assetTag;
        this.assetType = assetType;
        this.status = status;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getAssetTag() {
        return assetTag;
    }

    public void setAssetTag(String assetTag) {
        this.assetTag = assetTag;
    }

    public String getAssetType() {
        return assetType;
    }

    public void setAssetType(String assetType) {
        this.assetType = assetType;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public User getCurrentHolder() {
        return currentHolder;
    }

    public void setCurrentHolder(User currentHolder) {
        this.currentHolder = currentHolder;
    }

    public List<LifecycleEvent> getLifecycleEvents() {
        return lifecycleEvents;
    }

    public void setLifecycleEvents(List<LifecycleEvent> lifecycleEvents) {
        this.lifecycleEvents = lifecycleEvents;
    }

    public List<TransferRecord> getTransferRecords() {
        return transferRecords;
    }

    public void setTransferRecords(List<TransferRecord> transferRecords) {
        this.transferRecords = transferRecords;
    }

    public DisposalRecord getDisposalRecord() {
        return disposalRecord;
    }

    public void setDisposalRecord(DisposalRecord disposalRecord) {
        this.disposalRecord = disposalRecord;
    }
}
