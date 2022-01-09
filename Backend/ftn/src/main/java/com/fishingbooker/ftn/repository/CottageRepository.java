package com.fishingbooker.ftn.repository;

import com.fishingbooker.ftn.bom.adventures.AdventureReservation;
import com.fishingbooker.ftn.bom.cottages.Cottage;
import com.fishingbooker.ftn.repository.base.EntityRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
@Transactional
public interface CottageRepository extends EntityRepository<Cottage> {

    @Query("SELECT c FROM Cottage c WHERE c.cottageOwner.id=:id")
    List<Cottage> findByCottageOwnerId(@Param("id") long id);

}
