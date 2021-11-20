package com.fishingbooker.ftn.service.interfaces;

import com.fishingbooker.ftn.dto.AdventureDto;

import java.util.List;

public interface AdventureService {

    List<AdventureDto> findAll();

    AdventureDto findById(long id);

    void initAdventures();

}
