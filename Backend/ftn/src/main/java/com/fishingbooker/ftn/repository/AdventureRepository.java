package com.fishingbooker.ftn.repository;

import com.fishingbooker.ftn.bom.adventures.Adventure;
import com.fishingbooker.ftn.repository.base.EntityRepository;
import org.springframework.data.jpa.repository.Lock;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.QueryHints;
import org.springframework.stereotype.Repository;

import javax.persistence.LockModeType;
import javax.persistence.QueryHint;
import java.util.List;

@Repository
public interface AdventureRepository extends EntityRepository<Adventure> {

    @Query("SELECT a FROM Adventure a where a.instructor.id=?1 and a.deleted=false")
    List<Adventure> getInstructorAdventures(Long id);

    @Lock(LockModeType.PESSIMISTIC_WRITE)
    @Query ("SELECT a FROM Adventure a WHERE a.id=:id")
    @QueryHints({@QueryHint(name = "javax.persistence.lock.timeout", value ="0")})
    public Adventure getAdventure(Long id);
}
