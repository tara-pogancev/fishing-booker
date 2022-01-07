package com.fishingbooker.ftn.dto;

import lombok.Data;

import java.util.List;

@Data
public class InstructorNewReservationDto {
    private Long adventureId;
    private Long clientId;
    private Long startDate;
    private Long endDate;
    private List<AdventureUtilityDto> utilities;
    private Double price;
    private Integer guestNumber;
}
