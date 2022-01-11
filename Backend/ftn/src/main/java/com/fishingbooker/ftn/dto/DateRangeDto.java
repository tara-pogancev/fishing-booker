package com.fishingbooker.ftn.dto;

import lombok.Data;

import java.time.LocalDate;

@Data
public class DateRangeDto {

    private LocalDate startDate;
    private LocalDate endDate;
}
