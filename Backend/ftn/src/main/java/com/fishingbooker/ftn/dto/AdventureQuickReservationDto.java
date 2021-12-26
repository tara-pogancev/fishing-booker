package com.fishingbooker.ftn.dto;

import lombok.Data;

import javax.persistence.Column;
import java.time.LocalDate;
import java.util.List;

@Data
public class AdventureQuickReservationDto {

    private Long adventureId;
    private List<AdventureUtilityDto> adventureUtilityDtoList;
    private Integer guestLimit;
    private LocalDate actionStart;
    private LocalDate actionEnd;
    private Integer price;
}
