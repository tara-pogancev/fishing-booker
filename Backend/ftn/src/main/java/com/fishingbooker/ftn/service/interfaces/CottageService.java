package com.fishingbooker.ftn.service.interfaces;

import com.fishingbooker.ftn.bom.cottages.Cottage;
import com.fishingbooker.ftn.dto.CottageCreationDto;
import com.fishingbooker.ftn.dto.CottageDto;
import com.fishingbooker.ftn.dto.EntitySearchDto;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

public interface CottageService {

    List<Cottage> findAll();

    CottageDto findById(long id);

    Cottage get(Long id);

    List<CottageDto> findByCottageOwnerId(long id);

    Boolean delete(Long id);

    Long create(CottageCreationDto cottageDto);

    List<Cottage> filterByDate(LocalDateTime startDate, LocalDateTime endDate);

    List<Cottage> findFiltered(EntitySearchDto filterDto);

}
