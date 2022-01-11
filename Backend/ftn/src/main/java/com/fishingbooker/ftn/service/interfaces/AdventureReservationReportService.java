package com.fishingbooker.ftn.service.interfaces;

import com.fishingbooker.ftn.bom.reservation_report.AdventureReservationReport;
import com.fishingbooker.ftn.dto.CreateAdventureReservationReportDto;
import com.fishingbooker.ftn.dto.ReservationReportDto;

import java.util.List;

public interface AdventureReservationReportService {
    List<AdventureReservationReport> getUnprocessedReports();

    Long createAdventureReservationReport(CreateAdventureReservationReportDto adventureReportDto);

    Long givePenaltyToClient(ReservationReportDto reportId);

    Long forgiveClient(ReservationReportDto reservationReportDto);

    boolean existsReportForAdventureReservation(Long adventureReservationId);
}
