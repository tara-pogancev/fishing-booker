package com.fishingbooker.ftn.repository;

import com.fishingbooker.ftn.bom.users.BoatOwner;
import com.fishingbooker.ftn.repository.base.EntityRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BoatOwnerRepository extends EntityRepository<BoatOwner> {

    @Query("SELECT b from BoatOwner b where b.enabled=true")
    List<BoatOwner> getRegisteredBoatOwners();
}
