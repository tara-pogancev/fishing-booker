package com.fishingbooker.ftn.service.interfaces;

import com.fishingbooker.ftn.bom.cottages.Cottage;
import com.fishingbooker.ftn.dto.CottageCreationDto;
import com.fishingbooker.ftn.dto.CottageDto;

import java.util.List;

public interface CottageService {

    List<Cottage> findAll();

    CottageDto findById(long id);

    Cottage get(Long id);

    List<CottageDto> findByCottageOwnerId(long id);

    Boolean delete(Long id);

    Long create(CottageCreationDto cottageDto);
}
