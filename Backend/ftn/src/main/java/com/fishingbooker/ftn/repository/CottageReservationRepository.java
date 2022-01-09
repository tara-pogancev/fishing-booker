package com.fishingbooker.ftn.repository;

import com.fishingbooker.ftn.bom.adventures.AdventureReservation;
import com.fishingbooker.ftn.bom.cottages.CottageReservation;
import com.fishingbooker.ftn.repository.base.EntityRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CottageReservationRepository extends EntityRepository<CottageReservation> {

    @Query("SELECT r FROM CottageReservation r WHERE r.cottage.id = :id")
    List<CottageReservation> getCottageReservations(Long id);
}
