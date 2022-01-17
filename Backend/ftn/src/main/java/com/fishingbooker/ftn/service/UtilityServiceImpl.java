package com.fishingbooker.ftn.service;

import com.fishingbooker.ftn.bom.Utility;
import com.fishingbooker.ftn.bom.adventures.Adventure;
import com.fishingbooker.ftn.bom.adventures.AdventureUtility;
import com.fishingbooker.ftn.bom.adventures.QuickReservationUtility;
import com.fishingbooker.ftn.bom.cottages.Cottage;
import com.fishingbooker.ftn.bom.cottages.CottageUtility;
import com.fishingbooker.ftn.dto.AdventureUtilityDto;
import com.fishingbooker.ftn.dto.UtilityDto;
import com.fishingbooker.ftn.repository.AdventureUtilityRepository;
import com.fishingbooker.ftn.repository.CottageUtilityRepository;
import com.fishingbooker.ftn.repository.QuickReservationUtilityRepository;
import com.fishingbooker.ftn.repository.UtilityRepository;
import com.fishingbooker.ftn.service.interfaces.UtilityService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
@Transactional
@RequiredArgsConstructor
public class UtilityServiceImpl implements UtilityService {

    private final UtilityRepository utilityRepository;
    private final AdventureUtilityRepository adventureUtilityRepository;
    private final CottageUtilityRepository cottageUtilityRepository;
    private final QuickReservationUtilityRepository quickReservationUtilityRepository;

    @Override
    public List<Utility> get() {
        return utilityRepository.findAll();
    }

    @Override
    public Set<AdventureUtility> convertStringToUtility(List<AdventureUtilityDto> utilityDtos, Adventure adventure) {
        if (utilityDtos == null || utilityDtos.size() == 0)
            return null;
        List<AdventureUtility> utilities = utilityDtos.stream().map(utilityDto -> createUtilityObject(utilityDto, adventure)).collect(Collectors.toList());
        return new HashSet<AdventureUtility>(utilities);
    }

    @Override
    public Set<CottageUtility> convertStringToUtility(List<UtilityDto> utilityDtos, Cottage cottage) {
        if (utilityDtos == null || utilityDtos.size() == 0)
            return null;
        List<CottageUtility> utilities = utilityDtos.stream().map(utilityDto -> createUtilityObject(utilityDto, cottage)).collect(Collectors.toList());
        return new HashSet<CottageUtility>(utilities);
    }

    private AdventureUtility createUtilityObject(AdventureUtilityDto dto, Adventure adventure) {
        Utility utility;
        AdventureUtility adventureUtility;
        if (dto.getId() == -1) {
            utility = new Utility(dto.getName());
            utilityRepository.save(utility);
            adventureUtility = new AdventureUtility(utility, dto.getPrice(), adventure);
        } else {
            utility = utilityRepository.getById(dto.getUtilityId());
            adventureUtility = new AdventureUtility(utility, dto.getPrice(), adventure);
        }
        adventureUtilityRepository.save(adventureUtility);
        return adventureUtility;
    }

    private CottageUtility createUtilityObject(UtilityDto dto, Cottage cottage) {
        Utility utility;
        CottageUtility cottageUtility;
        if (dto.getId() == -1) {
            utility = new Utility(dto.getName());
            utilityRepository.save(utility);
            cottageUtility = new CottageUtility(utility, dto.getPrice(), cottage);
        } else {
            if (utilityRepository.exists(dto.getId())) {
                utility = utilityRepository.getById(dto.getId());
            } else {
                CottageUtility cu = cottageUtilityRepository.getById(dto.getId());
                utility = cu.getUtility();
            }
            cottageUtility = new CottageUtility(utility, dto.getPrice(), cottage);
        }
        cottageUtilityRepository.save(cottageUtility);
        return cottageUtility;
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
            quickReservationUtility = new QuickReservationUtility(utility, dto.getPrice());
        }
        quickReservationUtilityRepository.save(quickReservationUtility);
        return quickReservationUtility;
    }


}
