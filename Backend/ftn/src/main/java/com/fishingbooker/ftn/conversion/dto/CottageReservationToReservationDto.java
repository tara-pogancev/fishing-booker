package com.fishingbooker.ftn.conversion.dto;

import com.fishingbooker.ftn.bom.cottages.CottageReservation;
import com.fishingbooker.ftn.dto.ReservationDto;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import java.util.stream.Collectors;

@Component
public class CottageReservationToReservationDto implements Converter<CottageReservation, ReservationDto> {

    @Override
    public ReservationDto convert(CottageReservation source) {
        ReservationDto dto = new ReservationDto();
        dto.setUserId(source.getReservationClient().getId());
        dto.setEndDate(source.getReservationEnd());
        dto.setStartDate(source.getReservationStart());
        dto.setEntityId(source.getCottage().getId());
        dto.setId(source.getId());
        dto.setPeople(source.getGuestNumber());
        dto.setPrice(source.getPrice());
        dto.setUtilityIds(source.getUtilities().stream().map(x -> x.getId()).collect(Collectors.toSet()));
        dto.setEntityType("cottage");
        return dto;
    }
}
