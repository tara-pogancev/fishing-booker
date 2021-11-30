package com.fishingbooker.ftn.service.interfaces;

import com.fishingbooker.ftn.bom.boats.Boat;
import com.fishingbooker.ftn.dto.BoatDto;

import java.util.List;

public interface BoatService {

    List<Boat> findAll();

    BoatDto findById(long id);

    Long delete(Long id);
}
