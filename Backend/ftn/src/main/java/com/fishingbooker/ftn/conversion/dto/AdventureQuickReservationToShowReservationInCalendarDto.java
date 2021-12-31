package com.fishingbooker.ftn.conversion.dto;

import com.fishingbooker.ftn.bom.adventures.AdventureQuickReservation;
import com.fishingbooker.ftn.dto.ShowReservationInCalendarDto;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Component
public class AdventureQuickReservationToShowReservationInCalendarDto implements Converter<AdventureQuickReservation, ShowReservationInCalendarDto> {
    @Override
    public ShowReservationInCalendarDto convert(AdventureQuickReservation source) {
        ShowReservationInCalendarDto dto=new ShowReservationInCalendarDto();

        dto.setReservationId(source.getId());
        if (source.getReservationClient()!=null){
            dto.setClientName(source.getReservationClient().getName());
            dto.setClientSurname(source.getReservationClient().getLastName());
        }
        dto.setPrice(source.getPrice());
        dto.setGuestNumber(source.getGuestLimit());
        dto.setEnd(source.getActionEnd());
        dto.setStart(source.getActionStart());

        return dto;
    }
}
