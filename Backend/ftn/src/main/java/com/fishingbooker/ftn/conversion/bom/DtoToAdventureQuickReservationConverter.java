package com.fishingbooker.ftn.conversion.bom;

import com.fishingbooker.ftn.bom.Utility;
import com.fishingbooker.ftn.bom.adventures.Adventure;
import com.fishingbooker.ftn.bom.adventures.AdventureQuickReservation;
import com.fishingbooker.ftn.bom.adventures.AdventureUtility;
import com.fishingbooker.ftn.bom.adventures.QuickReservationUtility;
import com.fishingbooker.ftn.dto.AdventureQuickReservationDto;
import com.fishingbooker.ftn.dto.AdventureUtilityDto;
import com.fishingbooker.ftn.repository.AdventureRepository;
import com.fishingbooker.ftn.repository.AdventureUtilityRepository;
import com.fishingbooker.ftn.repository.QuickReservationUtilityRepository;
import com.fishingbooker.ftn.repository.UtilityRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor
public class DtoToAdventureQuickReservationConverter implements Converter<AdventureQuickReservationDto, AdventureQuickReservation> {

    private final AdventureRepository adventureRepository;
    private final UtilityRepository utilityRepository;
    private final QuickReservationUtilityRepository quickReservationUtilityRepository;

    @Override
    public AdventureQuickReservation convert(AdventureQuickReservationDto source) {
        AdventureQuickReservation adventureQuickReservation=new AdventureQuickReservation();
        Adventure adventure=adventureRepository.getById(source.getAdventureId());
        adventureQuickReservation.setAdventure(adventure);
        adventureQuickReservation.setActionStart(source.getActionStart());
        adventureQuickReservation.setActionEnd(source.getActionEnd());
        adventureQuickReservation.setGuestLimit(source.getGuestLimit());
        //adventureQuickReservation.setPrice(source.getPrice());
        adventureQuickReservation.setUtilities(convertUtilityDtoToUtility(source.getAdventureUtilityDtoList()));
        return adventureQuickReservation;
    }

    public Set<QuickReservationUtility> convertUtilityDtoToUtility(List<AdventureUtilityDto> utilityDtos) {
        if (utilityDtos == null || utilityDtos.size() == 0)
            return null;
        List<QuickReservationUtility> utilities = utilityDtos.stream().map(utilityDto -> createUtilityObject(utilityDto)).collect(Collectors.toList());
        return new HashSet<QuickReservationUtility>(utilities);
    }

    private QuickReservationUtility createUtilityObject(AdventureUtilityDto dto) {
        Utility utility;
        QuickReservationUtility quickReservationUtility;
        if (dto.getId() == -1) {
            utility = new Utility(dto.getName());
            utilityRepository.save(utility);
            quickReservationUtility = new QuickReservationUtility(utility, dto.getPrice());
        } else {
            utility = utilityRepository.getById(dto.getUtilityId());
            quickReservationUtility= new QuickReservationUtility(utility, dto.getPrice());
        }
        quickReservationUtilityRepository.save(quickReservationUtility);
        return quickReservationUtility;
    }
}
