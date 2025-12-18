package com.example.demo.entity;
import jakarta.persistence.*;
public class DisposalRecord{
    @Id
    private Long id;
    private Asset asset;
    private String disposalMethod;
    private LocalDate disposalDate;
    private User approvedBy;
    private String notes;
    private 
}