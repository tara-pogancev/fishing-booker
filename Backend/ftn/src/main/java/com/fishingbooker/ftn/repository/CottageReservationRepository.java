package com.fishingbooker.ftn.repository;

import com.fishingbooker.ftn.bom.cottages.CottageReservation;
import com.fishingbooker.ftn.repository.base.EntityRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CottageReservationRepository extends EntityRepository<CottageReservation> {

    @Query("SELECT r FROM CottageReservation r WHERE r.cottage.id = :id and r.isCanceled = false")
    List<CottageReservation> getCottageReservations(Long id);

    @Query("SELECT r FROM CottageReservation r WHERE r.cottage.cottageOwner.id = :id and r.isCanceled = false")
    List<CottageReservation> getCottageReservationsByCottageOwner(Long id);

    @Query("SELECT r FROM CottageReservation r WHERE r.reservationClient.id = :id AND r.isCanceled = true")
    List<CottageReservation> getClientCanceledCottageReservations(Long id);

}
