package com.fishingbooker.ftn.repository;

import com.fishingbooker.ftn.bom.boats.BoatQuickReservation;
import com.fishingbooker.ftn.bom.boats.BoatQuickReservation;
import com.fishingbooker.ftn.repository.base.EntityRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface BoatQuickReservationRepository extends EntityRepository<BoatQuickReservation> {
    @Query(value = "SELECT *\n" +
            "\tFROM public.boat_quick_reservation natural join public.quick_reservation\n" +
            "where boat_id=:id and  ((:startDate between action_start and action_end) or (:endDate  between action_start and action_end))", nativeQuery = true)
    List<BoatQuickReservation> getOverlappedWithNewAction(LocalDateTime startDate, LocalDateTime endDate, Long id);
}
