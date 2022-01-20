package com.fishingbooker.ftn.dto;

import lombok.Data;

import java.util.List;

@Data
public class NewReservationDto {
    private Long entityId;
    private Long clientId;
    private Long startDate;
    private Long endDate;
    private List<UtilityDto> utilities;
    private Double price;
    private Integer guestNumber;
}
