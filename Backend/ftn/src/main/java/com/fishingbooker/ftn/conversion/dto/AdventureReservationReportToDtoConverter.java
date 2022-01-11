package com.fishingbooker.ftn.conversion.dto;

import com.fishingbooker.ftn.bom.reservation_report.AdventureReservationReport;
import com.fishingbooker.ftn.dto.ReservationReportDto;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Component
public class AdventureReservationReportToDtoConverter implements Converter<AdventureReservationReport, ReservationReportDto> {
    @Override
    public ReservationReportDto convert(AdventureReservationReport source) {
        ReservationReportDto dto=new ReservationReportDto();
        dto.setReportId(source.getId());
        dto.setAdventureName(source.getAdventureReservation().getAdventure().getName());
        dto.setOwnerName(source.getAdventureReservation().getAdventure().getInstructor().getFullName());
        dto.setClientName(source.getAdventureReservation().getReservationClient().getFullName());
        dto.setComment(source.getComment());
        dto.setReportType("Adventure");
        return dto;
    }
}
