package com.fishingbooker.ftn.controller;

import com.fishingbooker.ftn.bom.boats.AvailableBoatTimePeriod;
import com.fishingbooker.ftn.dto.AvailableBoatTimePeriodDto;
import com.fishingbooker.ftn.service.interfaces.AvailableBoatTimePeriodService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/availableBoatTimePeriods")
public class AvailableBoatTimePeriodController {
    private final AvailableBoatTimePeriodService availableBoatTimePeriodService;

    @GetMapping()
    public List<AvailableBoatTimePeriodDto> get() {
        return availableBoatTimePeriodService.findAll();
    }

    @PostMapping()
    public AvailableBoatTimePeriod addAvailableBoatTimePeriod(@RequestBody AvailableBoatTimePeriodDto availableBoatTimePeriodDto) {
        return availableBoatTimePeriodService.create(availableBoatTimePeriodDto);
    }
}
