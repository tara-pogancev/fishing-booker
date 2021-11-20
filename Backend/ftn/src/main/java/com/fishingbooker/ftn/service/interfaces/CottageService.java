package com.fishingbooker.ftn.service.interfaces;

import com.fishingbooker.ftn.dto.BoatDto;
import com.fishingbooker.ftn.dto.CottageDto;

import java.util.List;

public interface CottageService {

    List<CottageDto> findAll();

    CottageDto findById(long id);

    void initCottages();

}
