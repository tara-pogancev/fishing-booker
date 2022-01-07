package com.fishingbooker.ftn.conversion.dto;

import com.fishingbooker.ftn.bom.adventures.AdventureReservation;
import com.fishingbooker.ftn.dto.ShowReservationInCalendarDto;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Component
public class ReservationToShowReservationInCalendarDto implements Converter<AdventureReservation, ShowReservationInCalendarDto> {
    @Override
    public ShowReservationInCalendarDto convert(AdventureReservation source) {
        ShowReservationInCalendarDto dto = new ShowReservationInCalendarDto();

        dto.setReservationId(source.getId());
        dto.setClientName(source.getReservationClient().getName());
        dto.setClientSurname(source.getReservationClient().getLastName());
        dto.setPrice(source.getPrice());
        dto.setGuestNumber(source.getGuestNumber());
        dto.setEnd(source.getReservationEnd());
        dto.setStart(source.getReservationStart());

        return dto;
    }
}
