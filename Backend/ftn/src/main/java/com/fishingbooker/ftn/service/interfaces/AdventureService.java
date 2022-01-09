package com.fishingbooker.ftn.service.interfaces;

import com.fishingbooker.ftn.bom.adventures.Adventure;
import com.fishingbooker.ftn.bom.adventures.AdventureQuickReservation;
import com.fishingbooker.ftn.bom.adventures.AdventureReservation;
import com.fishingbooker.ftn.bom.adventures.AdventureUtility;
import com.fishingbooker.ftn.bom.boats.BoatReservation;
import com.fishingbooker.ftn.dto.AdventureCreationDto;
import com.fishingbooker.ftn.dto.AdventureDto;
import com.fishingbooker.ftn.dto.EntitySearchDto;
import com.fishingbooker.ftn.dto.InstructorNewReservationDto;

import java.time.LocalDateTime;
import java.util.List;

public interface AdventureService {

    List<AdventureDto> findAll();

    AdventureDto findById(long id);

    Long create(AdventureCreationDto adventureDto);

    List<Adventure> getInstructorAdventures(Long id);

    Adventure get(Long id);

    Long save(AdventureCreationDto adventureDto);

    boolean deleteAdventure(Long id);

    List<Adventure> filterByDate(LocalDateTime startDate, LocalDateTime endDate);

    List<Adventure> findFiltered(EntitySearchDto filterDto);

    Long createQuickReservation(AdventureQuickReservation reservation);

    List<AdventureQuickReservation> getQuickReservations(Long id);

    Long createReservation(InstructorNewReservationDto dto);

    List<AdventureUtility> getAdventureUtilities(Long id);

}
