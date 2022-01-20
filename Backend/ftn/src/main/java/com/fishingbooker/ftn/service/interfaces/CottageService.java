package com.fishingbooker.ftn.service.interfaces;

import com.fishingbooker.ftn.bom.cottages.Cottage;
import com.fishingbooker.ftn.bom.cottages.CottageReservation;
import com.fishingbooker.ftn.bom.cottages.CottageUtility;
import com.fishingbooker.ftn.dto.*;
import org.springframework.cache.annotation.Cacheable;

import java.time.LocalDateTime;
import java.util.List;

public interface CottageService {

    List<Cottage> findAll();

    @Cacheable("cottageDto")
    CottageDto findById(long id);

    Cottage get(Long id);

    List<CottageDto> findByCottageOwnerId(long id);

    Boolean delete(Long id);

    Long create(CottageCreationDto cottageDto);

    List<Cottage> filterByDate(LocalDateTime startDate, LocalDateTime endDate);

    List<Cottage> findFiltered(EntitySearchDto filterDto, Long userId);

    List<CottageReservation> getReservationsByCottage(Long cottageId);

    Boolean isCottageAvailable(Cottage cottage, LocalDateTime start, LocalDateTime end);

    Long createQuickReservation(AdventureQuickReservationDto dto);

    List<CottageUtility> getCottageUtilities(Long id);

    Long createReservation(NewReservationDto dto);
}
