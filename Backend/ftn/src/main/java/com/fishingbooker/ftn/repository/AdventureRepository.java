package com.fishingbooker.ftn.repository;

import com.fishingbooker.ftn.bom.adventures.Adventure;
import com.fishingbooker.ftn.repository.base.EntityRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AdventureRepository extends EntityRepository<Adventure> {

}
