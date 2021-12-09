package com.fishingbooker.ftn.conversion.dto;

import com.fishingbooker.ftn.bom.adventures.Adventure;
import com.fishingbooker.ftn.bom.adventures.AdventureReservation;
import com.fishingbooker.ftn.dto.InstructorPastReservationsDto;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

/*private Long reservationId;
    private String reservationType;
    private Long adventureId;
    private String adventureName;
    private LocalDate startDate;
    private LocalDate endDate;
    private Integer numberOfPeople;
    private Double price;
    private Long clientId;
    private String clientName;
    private String clientLastName;
    private String clientMail;
    private String clientfullAddress;
    private String clientPhoneNumber;*/
@Component
public class ReservationToInstructorPastReservationDto implements Converter<AdventureReservation, InstructorPastReservationsDto> {
    @Override
    public InstructorPastReservationsDto convert(AdventureReservation source) {
        InstructorPastReservationsDto dto=new InstructorPastReservationsDto();
        dto.setReservationId(source.getId());
        dto.setAdventureId(source.getAdventure().getId());
        dto.setAdventureName(source.getAdventure().getName());
        dto.setStartDate(source.getReservationStart());
        dto.setEndDate(source.getReservationEnd());
        dto.setNumberOfPeople(source.getGuestNumber());
        dto.setPrice(source.getPrice());
        dto.setClientId(source.getReservationClient().getId());
        dto.setClientName(source.getReservationClient().getName());
        dto.setClientLastName(source.getReservationClient().getLastName());
        dto.setClientMail(source.getReservationClient().getEmail());
        dto.setClientfullAddress(source.getReservationClient().getUserAddress().getStreet()+','+source.getReservationClient().getUserAddress().getCity()+','+source.getReservationClient().getUserAddress().getCountry());
        dto.setClientPhoneNumber(source.getReservationClient().getPhone());
        return dto;
    }
}
