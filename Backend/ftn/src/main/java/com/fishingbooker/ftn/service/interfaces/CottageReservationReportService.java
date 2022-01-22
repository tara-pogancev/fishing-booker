package com.fishingbooker.ftn.service.interfaces;

import com.fishingbooker.ftn.bom.reservation_report.BoatReservationReport;
import com.fishingbooker.ftn.bom.reservation_report.CottageReservationReport;
import com.fishingbooker.ftn.dto.CreateAdventureReservationReportDto;
import com.fishingbooker.ftn.dto.ReservationReportDto;

import java.util.List;

public interface CottageReservationReportService {
    boolean existsReportForCottageReservation(Long cottageReservationId);

    Long createCottageReservationReport(CreateAdventureReservationReportDto adventureReportDto);

    Long givePenaltyForCottageReservation(ReservationReportDto reportId);

    Long forgiveClient(ReservationReportDto reportDto);

    List<CottageReservationReport> getUnprocessedReports();
}
