package com.fishingbooker.ftn.conversion.dto.reservationReports;

import com.fishingbooker.ftn.bom.reservation_report.BoatReservationReport;
import com.fishingbooker.ftn.bom.reservation_report.CottageReservationReport;
import com.fishingbooker.ftn.dto.ReservationReportDto;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Component
public class CottageReservationReportToDtoConverter implements Converter<CottageReservationReport, ReservationReportDto> {
    @Override
    public ReservationReportDto convert(CottageReservationReport source) {
        ReservationReportDto dto = new ReservationReportDto();
        dto.setReportId(source.getId());
        dto.setAdventureName(source.getCottageReservation().getCottage().getName());
        dto.setOwnerName(source.getCottageReservation().getCottage().getCottageOwner().getFullName());
        dto.setClientName(source.getCottageReservation().getReservationClient().getFullName());
        dto.setComment(source.getComment());
        dto.setReportType("Cottage");
        return dto;
    }
}
