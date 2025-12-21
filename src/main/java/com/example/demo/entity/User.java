package com.example.demo.entity;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import java.util.List;

@Entity
@Table(name = "users")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    private String email;
    private String role;

    @OneToMany(mappedBy = "currentHolder")
    @JsonManagedReference
    private List<Asset> assets;

    @OneToMany(mappedBy = "performedBy")
    @JsonManagedReference
    private List<LifecycleEvent> lifecycleEvents;

    @OneToMany(mappedBy = "approvedBy")
    @JsonManagedReference
    private List<TransferRecord> transferRecords;

    @OneToMany(mappedBy = "approvedBy")
    @JsonManagedReference
    private List<DisposalRecord> disposalRecords;

    public User() {
    }

    public User(String name, String email, String role) {
        this.name = name;
        this.email = email;
        this.role = role;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public List<Asset> getAssets() {
        return assets;
    }

    public void setAssets(List<Asset> assets) {
        this.assets = assets;
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

    public List<DisposalRecord> getDisposalRecords() {
        return disposalRecords;
    }

    public void setDisposalRecords(List<DisposalRecord> disposalRecords) {
        this.disposalRecords = disposalRecords;
    }
}
