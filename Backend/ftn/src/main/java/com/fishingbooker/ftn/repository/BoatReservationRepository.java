package com.fishingbooker.ftn.repository;

import com.fishingbooker.ftn.bom.boats.BoatReservation;
import com.fishingbooker.ftn.repository.base.EntityRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BoatReservationRepository extends EntityRepository<BoatReservation> {

    @Query("SELECT r FROM BoatReservation r WHERE r.boat.id = :id")
    List<BoatReservation> getBoatReservations(Long id);

}
