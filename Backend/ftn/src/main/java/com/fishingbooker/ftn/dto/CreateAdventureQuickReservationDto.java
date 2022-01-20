package com.fishingbooker.ftn.dto;

import lombok.Data;

import java.util.List;

@Data
public class CreateAdventureQuickReservationDto {

    private Long adventureId;
    private List<AdventureUtilityDto> adventureUtilityDtoList;
    private Integer guestLimit;
    private Long actionStart;
    private Long actionEnd;
    private Double price;
}
