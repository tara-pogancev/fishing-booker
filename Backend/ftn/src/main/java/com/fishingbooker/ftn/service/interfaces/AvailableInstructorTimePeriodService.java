package com.fishingbooker.ftn.service.interfaces;

import com.fishingbooker.ftn.bom.adventures.AvailableInstructorTimePeriod;
import com.fishingbooker.ftn.dto.AvailableInstructorTimePeriodDto;
import com.fishingbooker.ftn.dto.ChangeTimeSlotDto;

import java.util.List;

public interface AvailableInstructorTimePeriodService {

    List<AvailableInstructorTimePeriod> findAll(Long instructorId);

    Long create(AvailableInstructorTimePeriodDto availableTime);

    boolean delete(Long id);

    boolean update( ChangeTimeSlotDto dto);
}
