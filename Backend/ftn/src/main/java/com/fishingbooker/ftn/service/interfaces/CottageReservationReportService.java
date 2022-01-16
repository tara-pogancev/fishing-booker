package com.fishingbooker.ftn.service.interfaces;

import com.fishingbooker.ftn.dto.CreateAdventureReservationReportDto;
import com.fishingbooker.ftn.dto.ReservationReportDto;

public interface CottageReservationReportService {
    boolean existsReportForCottageReservation(Long cottageReservationId);

    Long createCottageReservationReport(CreateAdventureReservationReportDto adventureReportDto);

    Long givePenaltyForCottageReservation(ReservationReportDto reportId);
}
