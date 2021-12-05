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

    @Query(value = "SELECT *\n" +
            "\tFROM public.fishing_instructor INNER JOIN public.application_user ON public.fishing_instructor.id=public.application_user.id ",nativeQuery=true)
    FishingInstructor getInstructorById(Long id);
}
