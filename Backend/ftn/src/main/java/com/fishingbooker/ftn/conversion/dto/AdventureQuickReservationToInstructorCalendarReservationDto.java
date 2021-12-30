package com.fishingbooker.ftn.conversion.dto;

import com.fishingbooker.ftn.bom.adventures.AdventureQuickReservation;
import com.fishingbooker.ftn.dto.InstructorCalendarReservationDto;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Component
public class AdventureQuickReservationToInstructorCalendarReservationDto implements Converter<AdventureQuickReservation, InstructorCalendarReservationDto> {
    @Override
    public InstructorCalendarReservationDto convert(AdventureQuickReservation source) {
        InstructorCalendarReservationDto dto=new InstructorCalendarReservationDto();
        dto.setReservationId(source.getId());
        dto.setReserationType("Quick Reservation");
        dto.setStartDate(source.getActionStart());
        dto.setEndDate(source.getActionEnd());
        return dto;
    }
}
