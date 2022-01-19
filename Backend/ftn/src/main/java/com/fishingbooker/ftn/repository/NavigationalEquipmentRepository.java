package com.fishingbooker.ftn.repository;

import com.fishingbooker.ftn.bom.boats.NavigationalEquipment;
import com.fishingbooker.ftn.repository.base.EntityRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface NavigationalEquipmentRepository extends EntityRepository<NavigationalEquipment> {

    @Query("SELECT ne FROM NavigationalEquipment ne WHERE ne.name=:name")
    NavigationalEquipment getByName(@Param("name") String name);
}
