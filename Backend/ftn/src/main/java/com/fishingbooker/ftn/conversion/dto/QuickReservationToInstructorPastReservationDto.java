package com.fishingbooker.ftn.conversion.dto;

import com.fishingbooker.ftn.bom.adventures.AdventureQuickReservation;
import com.fishingbooker.ftn.bom.reservations.QuickReservation;
import com.fishingbooker.ftn.dto.InstructorPastReservationsDto;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Component
public class QuickReservationToInstructorPastReservationDto implements Converter<AdventureQuickReservation, InstructorPastReservationsDto> {
    @Override
    public InstructorPastReservationsDto convert(AdventureQuickReservation source) {
        InstructorPastReservationsDto dto = new InstructorPastReservationsDto();
        dto.setReservationId(source.getId());
        dto.setAdventureId(source.getAdventure().getId());
        dto.setAdventureName(source.getAdventure().getName());
        dto.setStartDate(source.getActionStart());
        dto.setEndDate(source.getActionEnd());
        dto.setNumberOfPeople(source.getGuestLimit());
        dto.setPrice(source.getPrice());
        dto.setClientId(source.getReservationClient().getId());
        dto.setClientName(source.getReservationClient().getName());
        dto.setClientLastName(source.getReservationClient().getLastName());
        dto.setClientMail(source.getReservationClient().getEmail());
        dto.setClientfullAddress(source.getReservationClient().getUserAddress().getStreet() + ',' + source.getReservationClient().getUserAddress().getCity() + ',' + source.getReservationClient().getUserAddress().getCountry());
        dto.setClientPhoneNumber(source.getReservationClient().getPhone());
        dto.setReservationType("Quick Reservation");
        return dto;
    }
}
