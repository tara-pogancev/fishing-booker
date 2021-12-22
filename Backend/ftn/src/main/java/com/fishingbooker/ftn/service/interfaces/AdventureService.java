package com.fishingbooker.ftn.service.interfaces;

import com.fishingbooker.ftn.bom.adventures.Adventure;
import com.fishingbooker.ftn.dto.AdventureCreationDto;
import com.fishingbooker.ftn.dto.AdventureDto;
import com.fishingbooker.ftn.dto.EntitySearchDto;

import java.time.LocalDate;
import java.util.List;

public interface AdventureService {

    List<AdventureDto> findAll();

    AdventureDto findById(long id);

    Long create(AdventureCreationDto adventureDto);

    List<Adventure> getInstructorAdventures(Long id);

    Adventure get(Long id);

    Long save(AdventureCreationDto adventureDto);

    boolean deleteAdventure(Long id);

    List<Adventure> filterByDate(LocalDate startDate, LocalDate endDate);

    List<Adventure> findFiltered(EntitySearchDto filterDto);
}
