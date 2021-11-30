package com.fishingbooker.ftn.repository;

import com.fishingbooker.ftn.bom.users.FishingInstructor;
import com.fishingbooker.ftn.repository.base.EntityRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface FishingInstructorRepository extends EntityRepository<FishingInstructor> {

    @Query("SELECT i from FishingInstructor i WHERE i.enabled=true")
    List<FishingInstructor> getEnabledInstructors();
}
