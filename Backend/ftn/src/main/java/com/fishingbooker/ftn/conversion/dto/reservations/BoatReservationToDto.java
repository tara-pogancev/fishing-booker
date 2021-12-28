package com.fishingbooker.ftn.conversion.dto.reservations;

import com.fishingbooker.ftn.bom.boats.BoatReservation;
import com.fishingbooker.ftn.dto.ViewReservationDto;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Component
public class BoatReservationToDto implements Converter<BoatReservation, ViewReservationDto> {

    @Override
    public ViewReservationDto convert(BoatReservation source) {
        ViewReservationDto dto = new ViewReservationDto();
        dto.setOwnerName(source.getBoat().getBoatOwner().getName() + " " + source.getBoat().getBoatOwner().getLastName());
        dto.setEndDate(source.getReservationEnd());
        dto.setStartDate(source.getReservationStart());
        dto.setEntityId(source.getBoat().getId());
        dto.setId(source.getId());
        dto.setPeople(source.getGuestNumber());
        dto.setPrice(source.getPrice());
        dto.setEntityName(source.getBoat().getName());
        dto.setEntityType("boat");
        return dto;
    }
}
