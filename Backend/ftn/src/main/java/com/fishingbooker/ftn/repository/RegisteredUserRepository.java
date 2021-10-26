package com.fishingbooker.ftn.repository;

import com.fishingbooker.ftn.bom.users.RegisteredUser;
import com.fishingbooker.ftn.repository.base.EntityRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RegisteredUserRepository extends EntityRepository<RegisteredUser> {

}
