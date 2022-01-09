package com.fishingbooker.ftn.service.interfaces;

import com.fishingbooker.ftn.bom.AvailableTimePeriod;
import com.fishingbooker.ftn.bom.adventures.AvailableInstructorTimePeriod;
import com.fishingbooker.ftn.bom.boats.AvailableBoatTimePeriod;
import com.fishingbooker.ftn.bom.cottages.AvailableCottageTimePeriod;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Set;

public interface DateService {

    boolean doPeriodsOverlap(LocalDateTime start1, LocalDateTime end1, LocalDateTime start2, LocalDateTime end2);

    boolean doDatesOverlapWithPeriodList(LocalDateTime start, LocalDateTime end, List<AvailableTimePeriod> periods);

    boolean doDatesOverlapWithPeriodSet(LocalDateTime start, LocalDateTime end, Set<AvailableTimePeriod> periods);

    boolean doDatesOverlapWithInstructorPeriodSet(LocalDateTime start, LocalDateTime end, Set<AvailableInstructorTimePeriod> periods);

    boolean doDatesOverlapWithBoatPeriodSet(LocalDateTime start, LocalDateTime end, Set<AvailableBoatTimePeriod> periods);

    boolean doDatesOverlapWithCottagePeriodSet(LocalDateTime start, LocalDateTime end, Set<AvailableCottageTimePeriod> periods);

    boolean doDatesMatchNearby(LocalDateTime start1, LocalDateTime end1, LocalDateTime start2, LocalDateTime end2);

}
