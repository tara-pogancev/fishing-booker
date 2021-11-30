package com.fishingbooker.ftn.service.interfaces;

import com.fishingbooker.ftn.bom.cottages.Cottage;
import com.fishingbooker.ftn.dto.CottageDto;

import java.util.List;

public interface CottageService {

    List<Cottage> findAll();

    CottageDto findById(long id);

    Long delete(Long id);
}
