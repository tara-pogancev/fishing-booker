package com.fishingbooker.ftn.repository;

import com.fishingbooker.ftn.bom.adventures.AvailableInstructorTimePeriod;
import com.fishingbooker.ftn.repository.base.EntityRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface AvailableInstructorTimeRepository extends EntityRepository<AvailableInstructorTimePeriod> {

    @Query(value = "SELECT *\n" +
            "\tFROM public.available_instructor_time_period natural join public.available_time_period\n" +
            "\tWHERE instructor=:instructorId and ((end_date between :newStartDate and :newEndDate) or (start_date  between :newStartDate and :newEndDate)) ", nativeQuery = true)
    List<AvailableInstructorTimePeriod> checkOverlaping(@Param("instructorId") Long instructorId, @Param("newStartDate") LocalDateTime newStartDate, @Param("newEndDate") LocalDateTime newEndDate);

    @Query(value = "SELECT *\n" +
            "\tFROM public.available_instructor_time_period natural join public.available_time_period\n" +
            "\tWHERE instructor=:instructorId and id!=:periodId and ((end_date between :newStartDate and :newEndDate) or (start_date  between :newStartDate and :newEndDate)) ", nativeQuery = true)
    List<AvailableInstructorTimePeriod> checkOverlapingForUpdate(@Param("periodId") Long periodId, @Param("instructorId") Long instructorId, @Param("newStartDate") LocalDateTime newStartDate, @Param("newEndDate") LocalDateTime newEndDate);

    @Query(value = "SELECT *\n" +
            "\tFROM public.available_instructor_time_period natural join public.available_time_period\n" +
            "\tWHERE instructor=:instructorId and (:start between start_date and end_date) and (:end between start_date and end_date); ", nativeQuery = true)
    List<AvailableInstructorTimePeriod> getAvailabilityForDate(@Param("start") LocalDateTime start, @Param("end") LocalDateTime end, @Param("instructorId") Long instructorId);
}
