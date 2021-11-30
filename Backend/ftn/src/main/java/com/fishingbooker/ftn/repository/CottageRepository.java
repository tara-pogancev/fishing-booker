package com.fishingbooker.ftn.repository;

import com.fishingbooker.ftn.bom.cottages.Cottage;
import com.fishingbooker.ftn.repository.base.EntityRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
@Transactional
public interface CottageRepository extends EntityRepository<Cottage> {

}
