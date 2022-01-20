package com.fishingbooker.ftn.dto;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class AvailableBoatTimePeriodDto {
    private long boatId;
    private LocalDateTime startDate;
    private LocalDateTime endDate;
}
