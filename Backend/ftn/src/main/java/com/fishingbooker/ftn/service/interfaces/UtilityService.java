package com.fishingbooker.ftn.service.interfaces;

import com.fishingbooker.ftn.bom.Utility;
import com.fishingbooker.ftn.bom.adventures.Adventure;
import com.fishingbooker.ftn.bom.adventures.AdventureUtility;
import com.fishingbooker.ftn.bom.adventures.QuickReservationUtility;
import com.fishingbooker.ftn.bom.boats.Boat;
import com.fishingbooker.ftn.bom.boats.BoatUtility;
import com.fishingbooker.ftn.bom.cottages.Cottage;
import com.fishingbooker.ftn.bom.cottages.CottageUtility;
import com.fishingbooker.ftn.dto.AdventureUtilityDto;
import com.fishingbooker.ftn.dto.UtilityDto;

import java.util.List;
import java.util.Set;

public interface UtilityService {
    List<Utility> get();

    Set<AdventureUtility> convertStringToUtility(List<AdventureUtilityDto> utilityDtos, Adventure adventure);

    Set<CottageUtility> convertStringToUtility(List<UtilityDto> utilityDtos, Cottage cottage);

    Set<BoatUtility> convertStringToUtility(List<UtilityDto> utilityDtos, Boat boat);

    Set<QuickReservationUtility> convertUtilityDtoToUtility(List<AdventureUtilityDto> utilityDtos);
}
