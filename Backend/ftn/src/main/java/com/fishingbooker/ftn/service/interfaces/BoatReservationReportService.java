package com.fishingbooker.ftn.service.interfaces;

import com.fishingbooker.ftn.dto.CreateAdventureReservationReportDto;
import com.fishingbooker.ftn.dto.ReservationReportDto;

public interface BoatReservationReportService {
    boolean existsReportForBoatReservation(Long boatReservationId);

    Long createBoatReservationReport(CreateAdventureReservationReportDto adventureReportDto);

    Long givePenaltyForBoatReservation(ReservationReportDto reportId);
}
