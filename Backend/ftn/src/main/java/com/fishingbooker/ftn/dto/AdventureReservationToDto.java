package com.fishingbooker.ftn.dto;

import com.fishingbooker.ftn.bom.adventures.AdventureReservation;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Component
public class AdventureReservationToDto implements Converter<AdventureReservation, ViewReservationDto> {

    @Override
    public ViewReservationDto convert(AdventureReservation source) {
        ViewReservationDto dto = new ViewReservationDto();
        dto.setOwnerName(source.getAdventure().getInstructor().getName() + " " + source.getAdventure().getInstructor().getLastName());
        dto.setEndDate(source.getReservationEnd());
        dto.setStartDate(source.getReservationStart());
        dto.setEntityId(source.getAdventure().getId());
        dto.setId(source.getId());
        dto.setPeople(source.getGuestNumber());
        dto.setPrice(source.getPrice());
        dto.setEntityName(source.getAdventure().getName());
        dto.setEntityType("adventure");
        return dto;
    }
}
