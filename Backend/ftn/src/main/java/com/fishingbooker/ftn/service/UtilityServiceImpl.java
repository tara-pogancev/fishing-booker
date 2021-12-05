package com.fishingbooker.ftn.service;

import com.fishingbooker.ftn.bom.Utility;
import com.fishingbooker.ftn.bom.adventures.Adventure;
import com.fishingbooker.ftn.bom.adventures.AdventureUtility;
import com.fishingbooker.ftn.dto.UtilityDto;
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
    @Override
    public List<Utility> get() {
        return utilityRepository.findAll();
    }

    @Override
    public Set<AdventureUtility> convertStringToUtility(List<UtilityDto> utilityDtos,Adventure adventure) {
        if (utilityDtos==null)
                return null;
        List<AdventureUtility> utilities=utilityDtos.stream().map(utilityDto -> createUtilityObject(utilityDto,adventure)).collect(Collectors.toList());
        return new HashSet<AdventureUtility>(utilities);
    }

    private AdventureUtility createUtilityObject(UtilityDto dto,Adventure adventure){
        Utility utility;
        AdventureUtility adventureUtility;
        if (dto.getId()==-1){
            utility=new Utility(dto.getName());
            adventureUtility=new AdventureUtility(utility,dto.getPrice(),adventure);
        }else{
            utility=utilityRepository.getById(dto.getId());
            adventureUtility=new AdventureUtility(utility,dto.getPrice(),adventure);
        }

        return adventureUtility;
    }


}
