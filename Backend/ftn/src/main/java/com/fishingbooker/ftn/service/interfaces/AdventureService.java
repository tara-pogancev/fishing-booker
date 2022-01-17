package com.fishingbooker.ftn.service.interfaces;

import com.fishingbooker.ftn.bom.adventures.Adventure;
import com.fishingbooker.ftn.bom.adventures.AdventureQuickReservation;
import com.fishingbooker.ftn.bom.adventures.AdventureUtility;
import com.fishingbooker.ftn.dto.*;
import org.springframework.cache.annotation.Cacheable;

import java.time.LocalDateTime;
import java.util.List;

public interface AdventureService {

    List<AdventureDto> findAll();

    @Cacheable("adventureDto")
    AdventureDto findById(long id);

    Long create(AdventureCreationDto adventureDto);

    List<Adventure> getInstructorAdventures(Long id);

    Adventure get(Long id);

    Long save(AdventureCreationDto adventureDto);

    boolean deleteAdventure(Long id);

    List<Adventure> filterByDate(LocalDateTime startDate, LocalDateTime endDate);

    List<Adventure> findFiltered(EntitySearchDto filterDto, Long userId);

    Long createQuickReservation(AdventureQuickReservationDto reservation);

    List<AdventureQuickReservation> getQuickReservations(Long id);

    Long createReservation(InstructorNewReservationDto dto);

    List<AdventureUtility> getAdventureUtilities(Long id);

    Boolean isAdventureAvailable(Adventure adventure, LocalDateTime start, LocalDateTime end);

}
