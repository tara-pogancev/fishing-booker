package com.fishingbooker.ftn.repository;

import com.fishingbooker.ftn.bom.adventures.AvailableInstructorTimePeriod;
import com.fishingbooker.ftn.bom.cottages.AvailableCottageTimePeriod;
import com.fishingbooker.ftn.repository.base.EntityRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface AvailableCottageTimePeriodRepository extends EntityRepository<AvailableCottageTimePeriod> {
    @Query(value = "SELECT *\n" +
            "\tFROM public.available_cottage_time_period natural join public.available_time_period\n" +
            "\tWHERE cottage=:cottageId and (:start between start_date and end_date) and (:end between start_date and end_date); ", nativeQuery = true)
    List<AvailableCottageTimePeriod> getAvailabilityForDate(@Param("start") LocalDateTime start, @Param("end") LocalDateTime end, @Param("cottageId") Long cottageId);
}
