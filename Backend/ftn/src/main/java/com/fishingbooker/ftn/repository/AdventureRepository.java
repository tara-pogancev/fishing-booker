package com.fishingbooker.ftn.repository;

import com.fishingbooker.ftn.bom.adventures.Adventure;
import com.fishingbooker.ftn.repository.base.EntityRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AdventureRepository extends EntityRepository<Adventure> {

    @Query("SELECT a FROM Adventure a where a.instructor.id=?1")
    List<Adventure> getInstructorAdventures(Long id);
}
