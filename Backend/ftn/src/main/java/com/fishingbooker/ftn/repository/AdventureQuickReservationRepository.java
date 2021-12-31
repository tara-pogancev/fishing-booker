package com.fishingbooker.ftn.repository;

import com.fishingbooker.ftn.bom.adventures.AdventureQuickReservation;
import com.fishingbooker.ftn.repository.base.EntityRepository;
import org.springframework.data.jpa.repository.Query;

import java.time.LocalDateTime;
import java.util.List;

public interface AdventureQuickReservationRepository extends EntityRepository<AdventureQuickReservation> {
    @Query(value = "SELECT *\n" +
            "\tFROM public.adventure_quick_reservation natural join public.quick_reservation\n" +
            "where adventure_id=:id and  ((action_end between :startDate and :endDate) and (action_start  between :startDate and :endDate))",nativeQuery = true)
    List<AdventureQuickReservation> getInSelectedDate(LocalDateTime startDate, LocalDateTime endDate, Long id);
}
