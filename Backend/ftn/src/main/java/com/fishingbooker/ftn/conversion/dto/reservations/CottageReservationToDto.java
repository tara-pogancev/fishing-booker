package com.fishingbooker.ftn.conversion.dto.reservations;


import com.fishingbooker.ftn.bom.cottages.CottageReservation;
import com.fishingbooker.ftn.dto.ViewReservationDto;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Component
public class CottageReservationToDto implements Converter<CottageReservation, ViewReservationDto> {

    @Override
    public ViewReservationDto convert(CottageReservation source) {
        ViewReservationDto dto = new ViewReservationDto();
        dto.setOwnerName(source.getCottage().getCottageOwner().getName() + " " + source.getCottage().getCottageOwner().getLastName());
        dto.setEndDate(source.getReservationEnd());
        dto.setStartDate(source.getReservationStart());
        dto.setEntityId(source.getCottage().getId());
        dto.setId(source.getId());
        dto.setPeople(source.getGuestNumber());
        dto.setPrice(source.getPrice());
        dto.setEntityName(source.getCottage().getName());
        dto.setEntityType("cottage");
        return dto;
    }

}
