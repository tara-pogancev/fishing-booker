package com.fishingbooker.ftn.repository;

import com.fishingbooker.ftn.bom.reservation_report.AdventureReservationReport;
import com.fishingbooker.ftn.bom.reservation_report.BoatReservationReport;
import com.fishingbooker.ftn.repository.base.EntityRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BoatReservationReportRepository extends EntityRepository<BoatReservationReport> {
    @Query(value = "SELECT *\n" +
            "\tFROM public.boat_reservation_report WHERE boat_reservation=:boatReservationId", nativeQuery = true)
    BoatReservationReport getReport(@Param("boatReservationId") Long boatReservationId);

    @Query("SELECT report FROM BoatReservationReport report WHERE report.processedByAdmin=false")
    List<BoatReservationReport> getUnprocessedReports();
}
