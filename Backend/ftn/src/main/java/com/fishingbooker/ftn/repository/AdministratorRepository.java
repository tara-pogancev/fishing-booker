package com.fishingbooker.ftn.repository;

import com.fishingbooker.ftn.bom.users.Administrator;
import com.fishingbooker.ftn.bom.users.RegisteredClient;
import com.fishingbooker.ftn.repository.base.EntityRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AdministratorRepository extends EntityRepository<Administrator> {

}
