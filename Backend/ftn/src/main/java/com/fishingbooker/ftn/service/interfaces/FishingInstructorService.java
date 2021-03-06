package com.fishingbooker.ftn.service.interfaces;

import com.fishingbooker.ftn.bom.adventures.AdventureQuickReservation;
import com.fishingbooker.ftn.bom.adventures.AdventureReservation;
import com.fishingbooker.ftn.bom.users.FishingInstructor;
import com.fishingbooker.ftn.dto.ApplicationUserDto;
import org.springframework.cache.annotation.Cacheable;

import java.time.LocalDate;
import java.util.List;

public interface FishingInstructorService {

    List<FishingInstructor> findAll();

    @Cacheable("fishingInstructor")
    FishingInstructor findById(Long id);

    FishingInstructor create(ApplicationUserDto userDto);

    List<FishingInstructor> getEnabledInstructors();

    Long delete(Long id);

    FishingInstructor update(FishingInstructor instructor);

    List<AdventureReservation> getInstructorReservations(Long id);

    List<AdventureReservation> getNonCanceledInstructorReservations(Long id);

    List<AdventureQuickReservation> getInstructorQuickReservations(Long id);

    List<AdventureReservation> getInstructorPastReservations(Long id);

    List<AdventureQuickReservation> getInsturctorPastQuickReservations(Long id);

    List<AdventureReservation> getReservationsInDate(LocalDate startDate, LocalDate endDate, Long id);
}
