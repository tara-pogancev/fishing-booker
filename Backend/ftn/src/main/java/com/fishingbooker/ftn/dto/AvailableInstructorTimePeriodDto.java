package com.fishingbooker.ftn.dto;

import lombok.Data;

@Data
public class AvailableInstructorTimePeriodDto {

    private Long instructorId;
    private Long id;
    private Long startDate;
    private Long endDate;
}
