package com.fishingbooker.ftn.service;

import com.fishingbooker.ftn.bom.Address;
import com.fishingbooker.ftn.bom.AvailableTimePeriod;
import com.fishingbooker.ftn.bom.RuleOfConduct;
import com.fishingbooker.ftn.bom.adventures.Adventure;
import com.fishingbooker.ftn.bom.adventures.AdventureUtility;
import com.fishingbooker.ftn.bom.adventures.FishingEquipment;
import com.fishingbooker.ftn.bom.users.FishingInstructor;
import com.fishingbooker.ftn.conversion.DataConverter;
import com.fishingbooker.ftn.dto.AdventureCreationDto;
import com.fishingbooker.ftn.dto.AdventureDto;
import com.fishingbooker.ftn.dto.EntitySearchDto;
import com.fishingbooker.ftn.dto.FishingEquipmentDto;
import com.fishingbooker.ftn.repository.AdventureRepository;
import com.fishingbooker.ftn.repository.FishingInstructorRepository;
import com.fishingbooker.ftn.service.interfaces.AdventureService;
import com.fishingbooker.ftn.service.interfaces.ImageService;
import com.fishingbooker.ftn.service.interfaces.RuleOfConductService;
import com.fishingbooker.ftn.service.interfaces.UtilityService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

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

        if (adventureRepository.exists(id)) {
            Adventure adventure = adventureRepository.getById(id);
            return converter.convert(adventure, AdventureDto.class);
        }
        return null;
    }

    @Override
    public Long create(AdventureCreationDto adventureDto) {
        Adventure adventure;
        if (adventureDto.getId() == -1) {
            adventure = new Adventure();
        } else {
            adventure = adventureRepository.getById(adventureDto.getId());
        }

        FishingInstructor instructor = instructorRepository.getInstructorById(adventureDto.getOwnerId());
        Address address = new Address();
        adventure.setName(adventureDto.getName());
        address.setCity(adventureDto.getCity());
        address.setCountry(adventureDto.getCountry());
        address.setStreet(adventureDto.getStreet());
        adventure.setAddress(address);
        adventure.setPrice(adventureDto.getPrice());
        adventure.setInstructor(instructor);
        List<RuleOfConduct> rules = ruleOfConductService.createRulesFromString(adventureDto.getRules());
        adventure.setRules(new HashSet<>(rules));
        adventure.setFishingEquipments(convertDtoToModel(adventureDto.getFishingEquipment()));
        adventure.setDescription(adventureDto.getDescription());
        adventure.setCancellationPercentageKeep(adventureDto.getCancellationFee());
        adventure.setGuestLimit(adventureDto.getGuestLimit());
        try {
            adventure.setImages(imageService.saveImages(adventureDto.getImages()));
        } catch (IOException e) {
            e.printStackTrace();
        }
        Adventure savedAdventure = adventureRepository.save(adventure);
        Set<AdventureUtility> utilities = utilityService.convertStringToUtility(adventureDto.getAdditionalServices(), savedAdventure);
        savedAdventure.setUtilities(utilities);
        return adventureRepository.save(savedAdventure).getId();
    }

    private Set<FishingEquipment> convertDtoToModel(List<FishingEquipmentDto> fishingEquipment) {
        return fishingEquipment.stream().map(fishingEquipmentDto -> createEquipment(fishingEquipmentDto)).collect(Collectors.toSet());

    }

    private FishingEquipment createEquipment(FishingEquipmentDto fishingEquipmentDto) {
        if (fishingEquipmentDto.getId() == -1) {
            return new FishingEquipment(fishingEquipmentDto.getFishingEquipmentName());
        } else {
            FishingEquipment equipment = new FishingEquipment();
            equipment.setId(fishingEquipmentDto.getId());
            equipment.setFishingEquipmentName(fishingEquipmentDto.getFishingEquipmentName());
            return equipment;
        }
    }

    @Override
    public List<Adventure> getInstructorAdventures(Long id) {
        return adventureRepository.getInstructorAdventures(id);
    }

    @Override
    public Adventure get(Long id) {
        return adventureRepository.getById(id);
    }

    @Override
    public Long save(AdventureCreationDto adventureDto) {
        return 1l;
    }

    @Override
    public boolean deleteAdventure(Long id) {
        Adventure adventure = adventureRepository.getById(id);
        if ((adventure.getAdventureReservations() == null || adventure.getAdventureReservations().size() == 0) && (adventure.getAdventureQuickReservations() == null || adventure.getAdventureQuickReservations().size() == 0)) {
            adventureRepository.deleteById(id);
            return true;
        }
        return false;
    }

    @Override
    public List<Adventure> filterByDate(LocalDate startDate, LocalDate endDate) {
        List<Adventure> adventures = new ArrayList<>();
        for (Adventure adventure : adventureRepository.findAll()) {
            for (AvailableTimePeriod period : adventure.getInstructor().getAvailableTimePeriods()) {
                if (period.getStartDate().isBefore(startDate) && period.getEndDate().isAfter(endDate)) {
                    adventures.add(adventure);
                    break;
                }
            }
        }
        return adventures;
    }

    @Override
    public List<Adventure> findFiltered(EntitySearchDto filterDto) {
        List<Adventure> adventures = new ArrayList<>();

        for (Adventure adventure : filterByDate(filterDto.startDate, filterDto.endDate)) {
            if (adventure.getGuestLimit() >= filterDto.people && (adventure.getAddress().getCountry().equals(filterDto.country) || filterDto.country.equals("")))
                adventures.add(adventure);
        }

        return adventures;
    }


}
