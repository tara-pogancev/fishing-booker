package com.fishingbooker.ftn.controller;

import com.fishingbooker.ftn.bom.cottages.AvailableCottageTimePeriod;
import com.fishingbooker.ftn.dto.AvailableCottageTimePeriodDto;
import com.fishingbooker.ftn.service.interfaces.AvailableCottageTimePeriodService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/availableCottageTimePeriods")
public class AvailableCottageTimePeriodController {
    private final AvailableCottageTimePeriodService availableCottageTimePeriodService;

    @GetMapping()
    public List<AvailableCottageTimePeriodDto> get() {
        return availableCottageTimePeriodService.findAll();
    }

    @PostMapping()
    public AvailableCottageTimePeriod addAvailableCottageTimePeriod(@RequestBody AvailableCottageTimePeriodDto availableCottageTimePeriodDto) {
        return availableCottageTimePeriodService.create(availableCottageTimePeriodDto);
    }
}
