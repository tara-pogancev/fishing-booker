package com.fishingbooker.ftn.dto;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class AdventureQuickReservationCalendarDto {

    private LocalDateTime startDate;
    private LocalDateTime endDate;
}
