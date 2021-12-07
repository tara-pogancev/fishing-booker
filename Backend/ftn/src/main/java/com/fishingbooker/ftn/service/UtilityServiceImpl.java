package com.fishingbooker.ftn.service;

import com.fishingbooker.ftn.bom.Utility;
import com.fishingbooker.ftn.bom.adventures.Adventure;
import com.fishingbooker.ftn.bom.adventures.AdventureUtility;
import com.fishingbooker.ftn.dto.AdventureUtilityDto;
import com.fishingbooker.ftn.dto.UtilityDto;
import com.fishingbooker.ftn.repository.AdventureUtilityRepository;
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
    @Override
    public List<Utility> get() {
        return utilityRepository.findAll();
    }

    @Override
    public Set<AdventureUtility> convertStringToUtility(List<AdventureUtilityDto> utilityDtos,Adventure adventure) {
        if (utilityDtos==null || utilityDtos.size()==0)
                return null;
        List<AdventureUtility> utilities=utilityDtos.stream().map(utilityDto -> createUtilityObject(utilityDto,adventure)).collect(Collectors.toList());
        return new HashSet<AdventureUtility>(utilities);
    }

    private AdventureUtility createUtilityObject(AdventureUtilityDto dto, Adventure adventure){
        Utility utility;
        AdventureUtility adventureUtility;
        if (dto.getId()==-1){
            utility=new Utility(dto.getName());
            utilityRepository.save(utility);
            adventureUtility=new AdventureUtility(utility,dto.getPrice(),adventure);
        }else{
            utility=utilityRepository.getById(dto.getUtilityId());
            adventureUtility=new AdventureUtility(utility,dto.getPrice(),adventure);
        }
        adventureUtilityRepository.save(adventureUtility);
        return adventureUtility;
    }


}
