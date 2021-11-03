package com.fishingbooker.ftn.repository;

import com.fishingbooker.ftn.bom.users.FishingInstructor;
import com.fishingbooker.ftn.bom.users.RegisteredClient;
import com.fishingbooker.ftn.repository.base.EntityRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FishingInstructorRepository extends EntityRepository<FishingInstructor> {

}
