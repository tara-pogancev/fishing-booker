package com.fishingbooker.ftn.dto;

import lombok.Data;

import java.time.LocalDate;

@Data
public class AvailableCottageTimePeriodDto {
    private long cottageId;
    private LocalDate startDate;
    private LocalDate endDate;
}
