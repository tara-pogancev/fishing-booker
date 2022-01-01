package com.fishingbooker.ftn.dto;

import com.fishingbooker.ftn.bom.adventures.Adventure;
import lombok.Data;

import java.util.List;
import java.util.Set;

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
