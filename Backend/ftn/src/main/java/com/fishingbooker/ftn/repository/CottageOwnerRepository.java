package com.fishingbooker.ftn.repository;

import com.fishingbooker.ftn.bom.users.CottageOwner;
import com.fishingbooker.ftn.repository.base.EntityRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CottageOwnerRepository extends EntityRepository<CottageOwner> {

    @Query("SELECT c from CottageOwner c WHERE c.enabled=true")
    List<CottageOwner> getRegisteredOwners();
}
