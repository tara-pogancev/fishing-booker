package com.fishingbooker.ftn.repository;

import com.fishingbooker.ftn.bom.cottages.CottageReservation;
import com.fishingbooker.ftn.repository.base.EntityRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface CottageReservationRepository extends EntityRepository<CottageReservation> {

    @Query("SELECT r FROM CottageReservation r WHERE r.cottage.id = :id and r.isCanceled = false")
    List<CottageReservation> getCottageReservations(Long id);

    @Query("SELECT r FROM CottageReservation r WHERE r.cottage.cottageOwner.id = :id and r.isCanceled = false")
    List<CottageReservation> getCottageReservationsByCottageOwner(Long id);

    @Query("SELECT r FROM CottageReservation r WHERE r.reservationClient.id = :id AND r.isCanceled = true")
    List<CottageReservation> getClientCanceledCottageReservations(Long id);

    @Query(value = "SELECT * FROM public.cottage_reservation natural join public.reservation\n" +
            "WHERE cottage_id=:id and ((:startDate between reservation_start and reservation_end) or (:endDate  between reservation_start and reservation_end))", nativeQuery = true)
    List<CottageReservation> getOverlappedWithNewAction(@Param("startDate") LocalDateTime startDate, @Param("endDate") LocalDateTime endDate, @Param("id") Long id);

}
