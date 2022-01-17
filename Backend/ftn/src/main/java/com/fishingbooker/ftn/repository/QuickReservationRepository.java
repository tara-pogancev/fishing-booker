package com.fishingbooker.ftn.repository;

import com.fishingbooker.ftn.bom.reservations.QuickReservation;
import com.fishingbooker.ftn.repository.base.EntityRepository;
import org.springframework.data.jpa.repository.Lock;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.QueryHints;
import org.springframework.stereotype.Repository;

import javax.persistence.LockModeType;
import javax.persistence.QueryHint;

@Repository
public interface QuickReservationRepository extends EntityRepository<QuickReservation> {

    @Query("SELECT r FROM QuickReservation r WHERE r.id = :id")
    @Lock(LockModeType.PESSIMISTIC_WRITE)
    @QueryHints({@QueryHint(name = "javax.persistence.lock.timeout", value = "10000")})
    QuickReservation getLock(Long id);

}
