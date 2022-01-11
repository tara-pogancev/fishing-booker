package com.fishingbooker.ftn.service;

import com.fishingbooker.ftn.bom.AvailableTimePeriod;
import com.fishingbooker.ftn.bom.adventures.AvailableInstructorTimePeriod;
import com.fishingbooker.ftn.bom.boats.AvailableBoatTimePeriod;
import com.fishingbooker.ftn.bom.cottages.AvailableCottageTimePeriod;
import com.fishingbooker.ftn.service.interfaces.DateService;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Set;

@Service
public class DateServiceImpl implements DateService {

    /***
     * Implies that start and end dates are respectfully in order!
     * Returns ture if they do overlap!
     * @param start1
     * @param end1
     * @param start2
     * @param end2
     * @return
     */

    @Override
    public boolean doPeriodsOverlap(LocalDateTime start1, LocalDateTime end1, LocalDateTime start2, LocalDateTime end2) {
        return !(end1.isBefore(start2) || end2.isBefore(start1)); // Condition without `!` means there is no overlap!
    }

    @Override
    public boolean doDatesOverlapWithPeriodList(LocalDateTime start, LocalDateTime end, List<AvailableTimePeriod> periods) {
        for (AvailableTimePeriod period : periods) {
            if (doPeriodsOverlap(start, end, period.getStartDate(), period.getEndDate())) {
                return true;
            }
        }

        return false;
    }

    @Override
    public boolean doDatesOverlapWithPeriodSet(LocalDateTime start, LocalDateTime end, Set<AvailableTimePeriod> periods) {
        for (AvailableTimePeriod period : periods) {
            if (doPeriodsOverlap(start, end, period.getStartDate(), period.getEndDate())) {
                return true;
            }
        }

        return false;
    }

    @Override
    public boolean doDatesOverlapWithInstructorPeriodSet(LocalDateTime start, LocalDateTime end, Set<AvailableInstructorTimePeriod> periods) {
        for (AvailableTimePeriod period : periods) {
            if (doPeriodsOverlap(start, end, period.getStartDate(), period.getEndDate())) {
                return true;
            }
        }

        return false;
    }

    @Override
    public boolean doDatesOverlapWithBoatPeriodSet(LocalDateTime start, LocalDateTime end, Set<AvailableBoatTimePeriod> periods) {
        for (AvailableTimePeriod period : periods) {
            if (doPeriodsOverlap(start, end, period.getStartDate(), period.getEndDate())) {
                return true;
            }
        }

        return false;
    }

    @Override
    public boolean doDatesOverlapWithCottagePeriodSet(LocalDateTime start, LocalDateTime end, Set<AvailableCottageTimePeriod> periods) {
        for (AvailableTimePeriod period : periods) {
            if (doPeriodsOverlap(start, end, period.getStartDate(), period.getEndDate())) {
                return true;
            }
        }

        return false;
    }

    /**
     * Takes the start of day for both start and both end dates and compares them.
     *
     * @param start1
     * @param end1
     * @param start2
     * @param end2
     * @return
     */
    @Override
    public boolean doDatesMatchNearby(LocalDateTime start1, LocalDateTime end1, LocalDateTime start2, LocalDateTime end2) {
        return (start1.toLocalDate().isEqual(start2.toLocalDate()) && end1.toLocalDate().isEqual(end2.toLocalDate()));
    }

}
