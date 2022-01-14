package com.fishingbooker.ftn.dto;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class InstructorBusinessReportDto {
    private Long id;
    private LocalDateTime startDate;
    private LocalDateTime endDate;
    private Double price;
    private String adventureName;
}
