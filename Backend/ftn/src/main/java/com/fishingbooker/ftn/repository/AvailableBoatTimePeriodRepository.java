package com.fishingbooker.ftn.repository;

import com.fishingbooker.ftn.bom.boats.AvailableBoatTimePeriod;
import com.fishingbooker.ftn.repository.base.EntityRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface AvailableBoatTimePeriodRepository extends EntityRepository<AvailableBoatTimePeriod> {
    @Query(value = "SELECT *\n" +
            "\tFROM public.available_boat_time_period natural join public.available_time_period\n" +
            "\tWHERE boat=:boatId and (:start between start_date and end_date) and (:end between start_date and end_date); ", nativeQuery = true)
    List<AvailableBoatTimePeriod> getAvailabilityForDate(@Param("start") LocalDateTime start, @Param("end") LocalDateTime end, @Param("boatId") Long boatId);
}
