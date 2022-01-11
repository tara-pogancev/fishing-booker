package com.fishingbooker.ftn.repository;

import com.fishingbooker.ftn.bom.reservation_report.AdventureReservationReport;
import com.fishingbooker.ftn.repository.base.EntityRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface AdventureReservationReportRepository extends EntityRepository<AdventureReservationReport> {

    @Query("SELECT report FROM AdventureReservationReport report WHERE report.processedByAdmin=false")
    List<AdventureReservationReport> getUnprocessedReports();

    @Query(value = "SELECT *\n" +
            "\tFROM public.adventure_reservation_report WHERE adventure_reservation=:adventureReservationId",nativeQuery = true)
    AdventureReservationReport getReport(@Param("adventureReservationId") Long adventureReservationId);
}
