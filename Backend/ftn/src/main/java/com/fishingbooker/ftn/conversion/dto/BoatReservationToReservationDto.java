package com.fishingbooker.ftn.conversion.dto;

import com.fishingbooker.ftn.bom.boats.BoatReservation;
import com.fishingbooker.ftn.dto.ReservationDto;
import com.fishingbooker.ftn.service.interfaces.BoatReservationReportService;
import lombok.RequiredArgsConstructor;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor
public class BoatReservationToReservationDto implements Converter<BoatReservation, ReservationDto> {

    private final BoatReservationReportService boatReservationReportService;

    @Override
    public ReservationDto convert(BoatReservation source) {
        ReservationDto dto = new ReservationDto();
        dto.setUserId(source.getReservationClient().getId());
        dto.setEndDate(source.getReservationEnd());
        dto.setStartDate(source.getReservationStart());
        dto.setEntityId(source.getBoat().getId());
        dto.setId(source.getId());
        dto.setPeople(source.getGuestNumber());
        dto.setPrice(source.getPrice());
        dto.setUtilityIds(source.getUtilities().stream().map(x -> x.getId()).collect(Collectors.toSet()));
        dto.setEntityType("boat");
        dto.setCreatedReport(boatReservationReportService.existsReportForBoatReservation(source.getId()));
        return dto;
    }
}
