package com.fishingbooker.ftn.repository;

import com.fishingbooker.ftn.bom.boats.Boat;
import com.fishingbooker.ftn.repository.base.EntityRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BoatRepository extends EntityRepository<Boat> {

}
