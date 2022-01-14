package com.fishingbooker.ftn.repository;

import com.fishingbooker.ftn.bom.reservations.QuickReservation;
import com.fishingbooker.ftn.repository.base.EntityRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface QuickReservationRepository extends EntityRepository<QuickReservation> {

}
