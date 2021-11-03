package com.fishingbooker.ftn.repository;

import com.fishingbooker.ftn.bom.users.CottageOwner;
import com.fishingbooker.ftn.bom.users.RegisteredClient;
import com.fishingbooker.ftn.repository.base.EntityRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CottageOwnerRepository extends EntityRepository<CottageOwner> {

}
