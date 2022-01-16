package com.fishingbooker.ftn.repository;

import com.fishingbooker.ftn.bom.cottages.Cottage;
import com.fishingbooker.ftn.repository.base.EntityRepository;
import org.springframework.data.jpa.repository.Lock;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.QueryHints;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import javax.persistence.LockModeType;
import javax.persistence.QueryHint;
import java.util.List;

@Repository
public interface CottageRepository extends EntityRepository<Cottage> {

    @Query("SELECT c FROM Cottage c WHERE c.cottageOwner.id=:id")
    List<Cottage> findByCottageOwnerId(@Param("id") long id);

    @Lock(LockModeType.PESSIMISTIC_WRITE)
    @Query("SELECT c FROM Cottage c WHERE c.id = :id")
    @QueryHints({@QueryHint(name = "javax.persistence.lock.timeout", value = "10000")})
    Cottage getLock(Long id);

}
