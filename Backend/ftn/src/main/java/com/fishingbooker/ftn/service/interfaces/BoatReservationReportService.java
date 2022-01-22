package com.fishingbooker.ftn.service.interfaces;

import com.fishingbooker.ftn.bom.reservation_report.AdventureReservationReport;
import com.fishingbooker.ftn.bom.reservation_report.BoatReservationReport;
import com.fishingbooker.ftn.dto.CreateAdventureReservationReportDto;
import com.fishingbooker.ftn.dto.ReservationReportDto;

import java.util.List;

public interface BoatReservationReportService {
    boolean existsReportForBoatReservation(Long boatReservationId);

    Long createBoatReservationReport(CreateAdventureReservationReportDto adventureReportDto);

    Long givePenaltyForBoatReservation(ReservationReportDto reportId);

    List<BoatReservationReport> getUnprocessedReports();

    Long forgiveClient(ReservationReportDto reportDto);
}
