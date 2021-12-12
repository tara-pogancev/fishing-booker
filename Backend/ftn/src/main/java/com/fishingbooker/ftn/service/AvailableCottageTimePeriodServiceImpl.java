package com.fishingbooker.ftn.service;

import com.fishingbooker.ftn.bom.cottages.AvailableCottageTimePeriod;
import com.fishingbooker.ftn.conversion.DataConverter;
import com.fishingbooker.ftn.dto.AvailableCottageTimePeriodDto;
import com.fishingbooker.ftn.repository.AvailableCottageTimePeriodRepository;
import com.fishingbooker.ftn.service.interfaces.AvailableCottageTimePeriodService;
import com.fishingbooker.ftn.service.interfaces.CottageService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;

@Service
@Transactional
@RequiredArgsConstructor
public class AvailableCottageTimePeriodServiceImpl implements AvailableCottageTimePeriodService {
    private final AvailableCottageTimePeriodRepository availableCottageTimePeriodRepository;
    private final CottageService cottageService;
    private final DataConverter converter;

    @Override
    public List<AvailableCottageTimePeriodDto> findAll() {
        return converter.convert(availableCottageTimePeriodRepository.findAll(), AvailableCottageTimePeriodDto.class);
    }

    @Override
    public AvailableCottageTimePeriod create(AvailableCottageTimePeriodDto availableCottageTimePeriodDto) {
        AvailableCottageTimePeriod availableCottageTimePeriod = new AvailableCottageTimePeriod();
        availableCottageTimePeriod.setCottage(cottageService.get(availableCottageTimePeriodDto.getCottageId()));
        availableCottageTimePeriod.setStartDate(availableCottageTimePeriodDto.getStartDate());
        availableCottageTimePeriod.setEndDate(availableCottageTimePeriodDto.getEndDate());

        return availableCottageTimePeriodRepository.save(availableCottageTimePeriod);
    }
}
