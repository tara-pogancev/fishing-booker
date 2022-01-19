package com.fishingbooker.ftn.service.interfaces;

import com.fishingbooker.ftn.bom.boats.AvailableBoatTimePeriod;
import com.fishingbooker.ftn.dto.AvailableBoatTimePeriodDto;

import java.util.List;

public interface AvailableBoatTimePeriodService {
    List<AvailableBoatTimePeriodDto> findAll();

    AvailableBoatTimePeriod create(AvailableBoatTimePeriodDto availableBoatTimePeriodDto);
}
