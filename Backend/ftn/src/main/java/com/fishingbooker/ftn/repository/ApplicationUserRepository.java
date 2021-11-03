package com.fishingbooker.ftn.repository;

import com.fishingbooker.ftn.bom.users.ApplicationUser;
import com.fishingbooker.ftn.repository.base.EntityRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ApplicationUserRepository extends EntityRepository<ApplicationUser> {
}
