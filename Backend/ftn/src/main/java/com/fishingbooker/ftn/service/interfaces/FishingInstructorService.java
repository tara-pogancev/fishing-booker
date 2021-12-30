package com.fishingbooker.ftn.service.interfaces;

import com.fishingbooker.ftn.bom.adventures.AdventureQuickReservation;
import com.fishingbooker.ftn.bom.adventures.AdventureReservation;
import com.fishingbooker.ftn.bom.users.FishingInstructor;
import com.fishingbooker.ftn.dto.ApplicationUserDto;

import java.util.List;

public interface FishingInstructorService {

    List<FishingInstructor> findAll();

    FishingInstructor findById(Long id);

    FishingInstructor create(ApplicationUserDto userDto);

    List<FishingInstructor> getEnabledInstructors();

    Long delete(Long id);

    FishingInstructor update(FishingInstructor instructor);

    List<AdventureReservation> getInstructorReservations(Long id);

    List<AdventureQuickReservation> getInstructorQuickReservations(Long id);
}
