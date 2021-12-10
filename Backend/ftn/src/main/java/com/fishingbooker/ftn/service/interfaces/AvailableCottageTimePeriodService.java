package com.fishingbooker.ftn.service.interfaces;

import com.fishingbooker.ftn.bom.cottages.AvailableCottageTimePeriod;
import com.fishingbooker.ftn.dto.AvailableCottageTimePeriodDto;

import java.util.List;

public interface AvailableCottageTimePeriodService {
    List<AvailableCottageTimePeriod> findAll();

    AvailableCottageTimePeriod create(AvailableCottageTimePeriodDto availableCottageTimePeriodDto);
}
