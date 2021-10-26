package com.fishingbooker.ftn.repository;

import com.fishingbooker.ftn.bom.cottages.Cottage;
import com.fishingbooker.ftn.repository.base.EntityRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CottageRepository extends EntityRepository<Cottage> {

}
