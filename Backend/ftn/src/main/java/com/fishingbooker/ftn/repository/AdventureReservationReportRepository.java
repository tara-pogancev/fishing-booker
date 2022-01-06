package com.fishingbooker.ftn.repository;

import com.fishingbooker.ftn.bom.reservation_report.AdventureReservationReport;
import com.fishingbooker.ftn.repository.base.EntityRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface AdventureReservationReportRepository extends EntityRepository<AdventureReservationReport> {

    @Query("SELECT report FROM AdventureReservationReport report WHERE report.processedByAdmin=false")
    List<AdventureReservationReport> getUnprocessedReports();
}
