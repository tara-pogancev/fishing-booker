package com.fishingbooker.ftn.conversion.dto.reservations;

import com.fishingbooker.ftn.bom.adventures.AdventureReservation;
import com.fishingbooker.ftn.dto.ViewReservationDto;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Component
public class AdventureReservationToDto implements Converter<AdventureReservation, ViewReservationDto> {

    @Override
    public ViewReservationDto convert(AdventureReservation source) {
        ViewReservationDto dto = new ViewReservationDto();
        dto.setEndDate(source.getReservationEnd());
        dto.setStartDate(source.getReservationStart());
        dto.setId(source.getId());
        dto.setPeople(source.getGuestNumber());
        dto.setPrice(source.getPrice());
        if (source.getAdventure()!=null){
            dto.setEntityType("adventure");
            dto.setEntityName(source.getAdventure().getName());
            dto.setEntityId(source.getAdventure().getId());
            dto.setOwnerName(source.getAdventure().getInstructor().getName() + " " + source.getAdventure().getInstructor().getLastName());
        }

        return dto;
    }
}
