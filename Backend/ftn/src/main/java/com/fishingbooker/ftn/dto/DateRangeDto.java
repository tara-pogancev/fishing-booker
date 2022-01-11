package com.fishingbooker.ftn.dto;

import lombok.Data;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Data
public class DateRangeDto {

    private LocalDate startDate;
    private LocalDate endDate;
}
