package com.example.demo.entity;

import jakarta.persistence.*;

@Entity
public class Asset{
    @Id 
    private Long id;
    @column(unique=true)
    private String assetTag;
    private String assetType;
    private String model;

} 
