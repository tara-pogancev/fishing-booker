package com.fishingbooker.ftn.dto;

import lombok.Data;

import java.time.LocalDateTime;
import java.util.List;

@Data
public class AdventureQuickReservationDto {

    private Long adventureId;
    private List<AdventureUtilityDto> adventureUtilityDtoList;
    private Integer guestLimit;
    private LocalDateTime actionStart;
    private LocalDateTime actionEnd;
    private Double price;
}
