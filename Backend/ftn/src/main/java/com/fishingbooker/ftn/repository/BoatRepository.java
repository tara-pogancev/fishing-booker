package com.fishingbooker.ftn.repository;

import com.fishingbooker.ftn.bom.boats.Boat;
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
public interface BoatRepository extends EntityRepository<Boat> {

    @Lock(LockModeType.PESSIMISTIC_WRITE)
    @Query("SELECT b FROM Boat b WHERE b.id = :id")
    @QueryHints({@QueryHint(name = "javax.persistence.lock.timeout", value = "10000")})
    Boat getLock(Long id);

    @Query("SELECT b FROM Boat b WHERE b.boatOwner.id=:id and b.deleted = false")
    List<Boat> findByBoatOwnerId(@Param("id") long id);
}
