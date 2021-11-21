package com.fishingbooker.ftn.service.interfaces;

import com.fishingbooker.ftn.dto.BoatDto;

import java.util.List;

public interface BoatService {

    List<BoatDto> findAll();

    BoatDto findById(long id);

}
