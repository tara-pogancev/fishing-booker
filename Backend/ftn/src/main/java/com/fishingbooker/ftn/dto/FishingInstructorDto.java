package com.fishingbooker.ftn.dto;

import com.fishingbooker.ftn.bom.AvailableTimePeriod;
import com.fishingbooker.ftn.bom.boats.AvailableBoatTimePeriod;
import lombok.Data;

import java.time.LocalDate;
import java.util.Set;

@Data
public class FishingInstructorDto extends ApplicationUserDto {

    private String biography;
    private Double rating;
    private Set<AvailableTimePeriod> availableTimePeriods;

}
