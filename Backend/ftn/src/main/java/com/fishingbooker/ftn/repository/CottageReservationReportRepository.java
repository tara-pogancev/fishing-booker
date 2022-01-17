package com.fishingbooker.ftn.repository;

import com.fishingbooker.ftn.bom.reservation_report.CottageReservationReport;
import com.fishingbooker.ftn.repository.base.EntityRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface CottageReservationReportRepository extends EntityRepository<CottageReservationReport> {
    @Query(value = "SELECT *\n" +
            "\tFROM public.cottage_reservation_report WHERE cottage_reservation=:cottageReservationId", nativeQuery = true)
    CottageReservationReport getReport(@Param("cottageReservationId") Long cottageReservationId);
}
