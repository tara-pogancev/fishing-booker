package com.fishingbooker.ftn.repository;

import com.fishingbooker.ftn.bom.cottages.Room;
import com.fishingbooker.ftn.repository.base.EntityRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;

@Repository
public interface RoomRepository extends EntityRepository<Room> {
    @Transactional
    @Modifying
    @Query("DELETE FROM Room r WHERE r.cottage.id = :cottageId")
    void deleteRoomsByCottageId(@Param("cottageId") long cottageId);
}
