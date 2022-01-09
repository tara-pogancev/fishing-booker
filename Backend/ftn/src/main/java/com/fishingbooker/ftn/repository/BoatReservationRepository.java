package com.fishingbooker.ftn.repository;

import com.fishingbooker.ftn.bom.boats.BoatReservation;
import com.fishingbooker.ftn.repository.base.EntityRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BoatReservationRepository extends EntityRepository<BoatReservation> {

    @Query("SELECT r FROM BoatReservation r WHERE r.boat.id = :id and r.isCanceled = false")
    List<BoatReservation> getBoatReservations(Long id);

    @Query("SELECT r FROM BoatReservation r WHERE r.reservationClient.id = :id AND r.isCanceled = true")
    List<BoatReservation> getClientCanceledBoatReservations(Long id);

}
