package com.fishingbooker.ftn.service;

import com.fishingbooker.ftn.bom.Address;
import com.fishingbooker.ftn.bom.RuleOfConduct;
import com.fishingbooker.ftn.bom.adventures.Adventure;
import com.fishingbooker.ftn.bom.adventures.AdventureUtility;
import com.fishingbooker.ftn.bom.users.ApplicationUser;
import com.fishingbooker.ftn.bom.users.FishingInstructor;
import com.fishingbooker.ftn.conversion.DataConverter;
import com.fishingbooker.ftn.dto.AdventureCreationDto;
import com.fishingbooker.ftn.dto.AdventureDto;
import com.fishingbooker.ftn.repository.AdventureRepository;
import com.fishingbooker.ftn.repository.ApplicationUserRepository;
import com.fishingbooker.ftn.repository.FishingInstructorRepository;
import com.fishingbooker.ftn.service.interfaces.AdventureService;
import com.fishingbooker.ftn.service.interfaces.ImageService;
import com.fishingbooker.ftn.service.interfaces.RuleOfConductService;
import com.fishingbooker.ftn.service.interfaces.UtilityService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.io.IOException;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Service
@Transactional
@RequiredArgsConstructor
public class AdventureServiceImpl implements AdventureService {

    private final DataConverter converter;
    private final AdventureRepository adventureRepository;
    private final FishingInstructorRepository instructorRepository;
    private final RuleOfConductService ruleOfConductService;
    private final ImageService imageService;
    private final UtilityService utilityService;

    @Override
    public List<AdventureDto> findAll() {
        List<Adventure> adventures = adventureRepository.findAll();
        return converter.convert(adventures, AdventureDto.class);
    }

    @Override
    public AdventureDto findById(long id) {
        Adventure adventure = adventureRepository.getById(id);
        return converter.convert(adventure, AdventureDto.class);
    }

    @Override
    public Long create(AdventureCreationDto adventureDto) {
        Adventure adventure=new Adventure();
        FishingInstructor instructor=instructorRepository.getInstructorById(adventureDto.getOwnerId());
        Address address=new Address();
        adventure.setName(adventureDto.getName());
        address.setCity(adventureDto.getCity());
        address.setCountry(adventureDto.getCountry());
        address.setStreet(adventureDto.getStreet());
        adventure.setAddress(address);
        adventure.setInstructor(instructor);
        List<RuleOfConduct> rules=ruleOfConductService.createRulesFromString(adventureDto.getRules());
        adventure.setRules(new HashSet<>(rules));
        adventure.setDescription(adventureDto.getDescription());
        adventure.setCancellationPercentageKeep(adventureDto.getCancellationFee());
        adventure.setGuestLimit(adventureDto.getGuestLimit());
        try {
            adventure.setImages(imageService.saveImages(adventureDto.getImages()));
        } catch (IOException e) {
            e.printStackTrace();
        }
        Adventure savedAdventure=adventureRepository.save(adventure);
        Set<AdventureUtility> utilities=utilityService.convertStringToUtility(adventureDto.getAdditionalServices(),savedAdventure);
        savedAdventure.setUtilities(utilities);
        return adventureRepository.save(savedAdventure).getId();
    }

}
