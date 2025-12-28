package com.example.demo.dto;

import lombok.Data;
import java.time.LocalDate;

@Data
public class TransferRequestDTO {
    private String fromDepartment;
    private String toDepartment;
    private LocalDate transferDate;
    private Long approvedByUserId;
}