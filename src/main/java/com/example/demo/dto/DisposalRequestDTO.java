package com.example.demo.dto;

import lombok.Data;
import java.time.LocalDate;

@Data
public class DisposalRequestDTO {
    private String disposalMethod;
    private LocalDate disposalDate;
    private Long approvedByUserId;
    private String notes;
}