package com.example.demo.entity;

import jakarta.persistence.*;
public class TransferRecord{
    @Id
    private Long id;
    private Asset asset;
    private String fromDepartment;
    private String toDepartment;
    private LocalDate transferDate;
    private User approvedBy;
}