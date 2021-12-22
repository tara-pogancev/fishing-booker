package com.fishingbooker.ftn.repository;

import com.fishingbooker.ftn.bom.boats.BoatReservation;
import com.fishingbooker.ftn.repository.base.EntityRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BoatReservationRepository extends EntityRepository<BoatReservation> {
}
