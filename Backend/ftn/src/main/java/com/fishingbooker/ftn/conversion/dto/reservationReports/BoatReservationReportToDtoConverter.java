package com.fishingbooker.ftn.conversion.dto.reservationReports;

import com.fishingbooker.ftn.bom.reservation_report.AdventureReservationReport;
import com.fishingbooker.ftn.bom.reservation_report.BoatReservationReport;
import com.fishingbooker.ftn.dto.ReservationReportDto;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Component
public class BoatReservationReportToDtoConverter implements Converter<BoatReservationReport, ReservationReportDto> {
    @Override
    public ReservationReportDto convert(BoatReservationReport source) {
        ReservationReportDto dto = new ReservationReportDto();
        dto.setReportId(source.getId());
        dto.setAdventureName(source.getBoatReservation().getBoat().getName());
        dto.setOwnerName(source.getBoatReservation().getBoat().getBoatOwner().getFullName());
        dto.setClientName(source.getBoatReservation().getReservationClient().getFullName());
        dto.setComment(source.getComment());
        dto.setReportType("Boat");
        return dto;
    }
}