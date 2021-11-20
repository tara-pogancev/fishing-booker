package com.fishingbooker.ftn.repository;

import com.fishingbooker.ftn.bom.cottages.CottageUtility;
import com.fishingbooker.ftn.repository.base.EntityRepository;
import org.springframework.stereotype.Repository;

import javax.xml.stream.events.EndDocument;

@Repository
public interface CottageUtilityRepository extends EntityRepository<CottageUtility> {
}
