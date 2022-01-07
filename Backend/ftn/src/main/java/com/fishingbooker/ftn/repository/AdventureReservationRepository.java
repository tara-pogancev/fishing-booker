package com.fishingbooker.ftn.repository;

import com.fishingbooker.ftn.bom.adventures.AdventureReservation;
import com.fishingbooker.ftn.repository.base.EntityRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface AdventureReservationRepository extends EntityRepository<AdventureReservation> {

    @Query(value = "SELECT * FROM public.adventure_reservation natural join public.reservation\n" +
            "WHERE adventure_id=:id and ((reservation_end between :startDate and :endDate) and (reservation_start  between :startDate and :endDate))", nativeQuery = true)
    List<AdventureReservation> getInSelectedDate(@Param("startDate") LocalDateTime startDate, @Param("endDate") LocalDateTime endDate, @Param("id") Long id);

    @Query(value = "SELECT * FROM public.adventure_reservation natural join public.reservation\n" +
            "WHERE adventure_id=:id and ((:startDate between reservation_start and reservation_end) or (:endDate  between reservation_start and reservation_end))", nativeQuery = true)
    List<AdventureReservation> getOverlappedWithNewAction(@Param("startDate") LocalDateTime startDate, @Param("endDate") LocalDateTime endDate, @Param("id") Long id);


}
