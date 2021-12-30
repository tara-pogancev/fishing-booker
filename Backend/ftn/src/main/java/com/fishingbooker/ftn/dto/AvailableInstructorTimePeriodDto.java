package com.fishingbooker.ftn.dto;

import lombok.Data;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Data
public class AvailableInstructorTimePeriodDto {

    private Long instructorId;
    private Long id;
    private Long startDate;
    private Long endDate;
}
