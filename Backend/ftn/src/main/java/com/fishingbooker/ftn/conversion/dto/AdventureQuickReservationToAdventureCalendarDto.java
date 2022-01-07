package com.fishingbooker.ftn.conversion.dto;

import com.fishingbooker.ftn.bom.adventures.AdventureQuickReservation;
import com.fishingbooker.ftn.dto.AdventureQuickReservationCalendarDto;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Component
public class AdventureQuickReservationToAdventureCalendarDto implements Converter<AdventureQuickReservation, AdventureQuickReservationCalendarDto> {
    @Override
    public AdventureQuickReservationCalendarDto convert(AdventureQuickReservation source) {
        AdventureQuickReservationCalendarDto dto = new AdventureQuickReservationCalendarDto();
        dto.setStartDate(source.getActionStart());
        dto.setEndDate(source.getActionEnd());
        return dto;
    }
}
