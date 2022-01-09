package com.fishingbooker.ftn.service;

import com.fishingbooker.ftn.service.interfaces.DateService;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

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

}
