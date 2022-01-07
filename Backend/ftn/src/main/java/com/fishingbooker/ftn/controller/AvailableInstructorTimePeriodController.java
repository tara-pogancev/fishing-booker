package com.fishingbooker.ftn.controller;

import com.fishingbooker.ftn.bom.adventures.AvailableInstructorTimePeriod;
import com.fishingbooker.ftn.dto.AvailableInstructorTimePeriodDto;
import com.fishingbooker.ftn.dto.ChangeTimeSlotDto;
import com.fishingbooker.ftn.service.interfaces.AvailableInstructorTimePeriodService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/available-instructor-time")
public class AvailableInstructorTimePeriodController {

    private final AvailableInstructorTimePeriodService availableInstructorTimePeriodService;

    @GetMapping("/{id}")
    public List<AvailableInstructorTimePeriod> get(@PathVariable("id") Long instructorId) {
        return availableInstructorTimePeriodService.findAll(instructorId);
    }

    @PostMapping
    public Long createOrUpdate(@RequestBody AvailableInstructorTimePeriodDto availableTime) {
        return availableInstructorTimePeriodService.create(availableTime);
    }

    @DeleteMapping("/{id}")
    public boolean delete(@PathVariable("id") Long id) {
        return availableInstructorTimePeriodService.delete(id);
    }

    @PutMapping()
    public boolean update(@RequestBody ChangeTimeSlotDto dto) {
        return availableInstructorTimePeriodService.update(dto);
    }


}
