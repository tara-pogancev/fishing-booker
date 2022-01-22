package com.fishingbooker.ftn.controller;

import com.fishingbooker.ftn.bom.adventures.AvailableInstructorTimePeriod;
import com.fishingbooker.ftn.dto.AvailableInstructorTimePeriodDto;
import com.fishingbooker.ftn.dto.ChangeTimeSlotDto;
import com.fishingbooker.ftn.service.interfaces.AvailableInstructorTimePeriodService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/available-instructor-time")
public class AvailableInstructorTimePeriodController {

    private final AvailableInstructorTimePeriodService availableInstructorTimePeriodService;

    @GetMapping("/{id}")
    @PreAuthorize("hasRole('FISHING_INSTRUCTOR')")
    public List<AvailableInstructorTimePeriod> get(@PathVariable("id") Long instructorId) {
        return availableInstructorTimePeriodService.findAll(instructorId);
    }

    @PreAuthorize("hasRole('FISHING_INSTRUCTOR')")
    @PostMapping
    public HttpEntity<Long> createOrUpdate(@RequestBody AvailableInstructorTimePeriodDto availableTime) {
        Long id=availableInstructorTimePeriodService.create(availableTime);
        if (id!=-1l){
            return new ResponseEntity<>(id, HttpStatus.OK);
        }else{
            return new ResponseEntity<>(id,HttpStatus.BAD_REQUEST);
        }

    }

    @PreAuthorize("hasRole('FISHING_INSTRUCTOR')")
    @DeleteMapping("/{id}")
    public boolean delete(@PathVariable("id") Long id) {
        return availableInstructorTimePeriodService.delete(id);
    }

    @PreAuthorize("hasRole('FISHING_INSTRUCTOR')")
    @PutMapping()
    public boolean update(@RequestBody ChangeTimeSlotDto dto) {
        return availableInstructorTimePeriodService.update(dto);
    }


}
