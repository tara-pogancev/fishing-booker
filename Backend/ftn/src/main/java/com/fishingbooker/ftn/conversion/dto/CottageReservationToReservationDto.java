package com.fishingbooker.ftn.conversion.dto;

import com.fishingbooker.ftn.bom.cottages.CottageReservation;
import com.fishingbooker.ftn.dto.ReservationDto;
import com.fishingbooker.ftn.dto.ViewReservationDto;
import com.fishingbooker.ftn.service.CottageReservationReportServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor
public class CottageReservationToReservationDto implements Converter<CottageReservation, ReservationDto> {

    private final CottageReservationReportServiceImpl cottageReservationReportService;

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
        dto.setCreatedReport(cottageReservationReportService.existsReportForCottageReservation(source.getId()));
        return dto;
    }
}
