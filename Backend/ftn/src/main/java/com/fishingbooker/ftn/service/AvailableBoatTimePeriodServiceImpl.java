package com.fishingbooker.ftn.service;

import com.fishingbooker.ftn.bom.boats.AvailableBoatTimePeriod;
import com.fishingbooker.ftn.conversion.DataConverter;
import com.fishingbooker.ftn.dto.AvailableBoatTimePeriodDto;
import com.fishingbooker.ftn.repository.AvailableBoatTimePeriodRepository;
import com.fishingbooker.ftn.service.interfaces.AvailableBoatTimePeriodService;
import com.fishingbooker.ftn.service.interfaces.BoatService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
@Transactional
@RequiredArgsConstructor
public class AvailableBoatTimePeriodServiceImpl implements AvailableBoatTimePeriodService {
    private final AvailableBoatTimePeriodRepository availableBoatTimePeriodRepository;
    private final BoatService boatService;
    private final DataConverter converter;

    @Override
    public List<AvailableBoatTimePeriodDto> findAll() {
        return converter.convert(availableBoatTimePeriodRepository.findAll(), AvailableBoatTimePeriodDto.class);
    }

    @Override
    public AvailableBoatTimePeriod create(AvailableBoatTimePeriodDto availableBoatTimePeriodDto) {
        AvailableBoatTimePeriod availableBoatTimePeriod = new AvailableBoatTimePeriod();
        availableBoatTimePeriod.setBoat(boatService.get(availableBoatTimePeriodDto.getBoatId()));
        availableBoatTimePeriod.setStartDate(availableBoatTimePeriodDto.getStartDate());
        availableBoatTimePeriod.setEndDate(availableBoatTimePeriodDto.getEndDate());

        return availableBoatTimePeriodRepository.save(availableBoatTimePeriod);
    }
}
