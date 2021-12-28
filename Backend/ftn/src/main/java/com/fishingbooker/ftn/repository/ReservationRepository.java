package com.fishingbooker.ftn.repository;

import com.fishingbooker.ftn.bom.reservations.Reservation;
import com.fishingbooker.ftn.repository.base.EntityRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ReservationRepository extends EntityRepository<Reservation> {


}
