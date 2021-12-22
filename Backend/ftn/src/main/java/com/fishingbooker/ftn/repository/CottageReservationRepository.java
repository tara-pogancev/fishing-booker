package com.fishingbooker.ftn.repository;

import com.fishingbooker.ftn.bom.cottages.CottageReservation;
import com.fishingbooker.ftn.repository.base.EntityRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CottageReservationRepository extends EntityRepository<CottageReservation> {
}
