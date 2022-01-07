package com.fishingbooker.ftn.service.interfaces;

import com.fishingbooker.ftn.bom.adventures.AdventureQuickReservation;
import com.fishingbooker.ftn.bom.boats.BoatQuickReservation;
import com.fishingbooker.ftn.bom.cottages.CottageQuickReservation;
import com.fishingbooker.ftn.dto.ActionDto;

import java.util.List;

public interface ActionService {

    List<CottageQuickReservation> findAllCottage();

    List<BoatQuickReservation> findAllBoat();

    List<AdventureQuickReservation> findAllAdventure();

    List<ActionDto> findAll();

}
