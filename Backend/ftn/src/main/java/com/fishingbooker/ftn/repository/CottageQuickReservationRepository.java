package com.fishingbooker.ftn.repository;

import com.fishingbooker.ftn.bom.cottages.CottageQuickReservation;
import com.fishingbooker.ftn.repository.base.EntityRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface CottageQuickReservationRepository extends EntityRepository<CottageQuickReservation> {
    @Query(value = "SELECT *\n" +
            "\tFROM public.cottage_quick_reservation natural join public.quick_reservation\n" +
            "where cottage_id=:id and  ((:startDate between action_start and action_end) or (:endDate  between action_start and action_end))", nativeQuery = true)
    List<CottageQuickReservation> getOverlappedWithNewAction(LocalDateTime startDate, LocalDateTime endDate, Long id);
}
