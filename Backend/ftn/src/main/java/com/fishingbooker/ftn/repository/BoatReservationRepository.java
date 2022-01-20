package com.fishingbooker.ftn.repository;

import com.fishingbooker.ftn.bom.boats.BoatReservation;
import com.fishingbooker.ftn.bom.boats.BoatReservation;
import com.fishingbooker.ftn.repository.base.EntityRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface BoatReservationRepository extends EntityRepository<BoatReservation> {

    @Query("SELECT r FROM BoatReservation r WHERE r.boat.id = :id and r.isCanceled = false")
    List<BoatReservation> getBoatReservations(Long id);

    @Query("SELECT r FROM BoatReservation r WHERE r.reservationClient.id = :id AND r.isCanceled = true")
    List<BoatReservation> getClientCanceledBoatReservations(Long id);

    @Query("SELECT r FROM BoatReservation r WHERE r.boat.boatOwner.id = :id and r.isCanceled = false")
    List<BoatReservation> getBoatReservationsByBoatOwner(Long id);

    @Query(value = "SELECT * FROM public.boat_reservation natural join public.reservation\n" +
            "WHERE boat_id=:id and ((:startDate between reservation_start and reservation_end) or (:endDate  between reservation_start and reservation_end))", nativeQuery = true)
    List<BoatReservation> getOverlappedWithNewAction(@Param("startDate") LocalDateTime startDate, @Param("endDate") LocalDateTime endDate, @Param("id") Long id);

}
