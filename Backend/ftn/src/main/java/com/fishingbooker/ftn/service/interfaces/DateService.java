package com.fishingbooker.ftn.service.interfaces;

import java.time.LocalDate;
import java.time.LocalDateTime;

public interface DateService {

    boolean doPeriodsOverlap(LocalDateTime start1, LocalDateTime end1, LocalDateTime start2, LocalDateTime end2);

}
