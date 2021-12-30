package com.fishingbooker.ftn.dto;

import lombok.Data;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Data
public class AvailableCottageTimePeriodDto {
    private long cottageId;
    private LocalDateTime startDate;
    private LocalDateTime endDate;
}
