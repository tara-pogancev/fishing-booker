package com.fishingbooker.ftn.conversion.dto;

import com.fishingbooker.ftn.bom.adventures.AdventureReservation;
import com.fishingbooker.ftn.dto.InstructorCalendarReservationDto;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Component
public class AdventureReservationToInstructorCalendarReservationDto implements Converter<AdventureReservation, InstructorCalendarReservationDto> {
    @Override
    public InstructorCalendarReservationDto convert(AdventureReservation source) {
        InstructorCalendarReservationDto dto = new InstructorCalendarReservationDto();
        dto.setReservationId(source.getId());
        dto.setReservationType("Reservation");
        dto.setStartDate(source.getReservationStart());
        dto.setEndDate(source.getReservationEnd());
        return dto;
    }
}
