package com.fishingbooker.ftn.dto;

import lombok.Data;

@Data
public class ChangeTimeSlotDto {
    private Long instructorId;
    private Long id;
    private Long startDate;
    private Long endDate;
    private Long newStartDate;
    private Long newEndDate;

}
