package com.fishingbooker.ftn.service;

import com.fishingbooker.ftn.bom.Address;
import com.fishingbooker.ftn.bom.AvailableTimePeriod;
import com.fishingbooker.ftn.bom.RuleOfConduct;
import com.fishingbooker.ftn.bom.cottages.Cottage;
import com.fishingbooker.ftn.bom.cottages.CottageUtility;
import com.fishingbooker.ftn.bom.cottages.Room;
import com.fishingbooker.ftn.bom.users.CottageOwner;
import com.fishingbooker.ftn.conversion.DataConverter;
import com.fishingbooker.ftn.dto.CottageCreationDto;
import com.fishingbooker.ftn.dto.CottageDto;
import com.fishingbooker.ftn.dto.EntitySearchDto;
import com.fishingbooker.ftn.dto.RoomDto;
import com.fishingbooker.ftn.repository.CottageOwnerRepository;
import com.fishingbooker.ftn.repository.CottageRepository;
import com.fishingbooker.ftn.service.interfaces.CottageService;
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
public class CottageServiceImpl implements CottageService {

    private final CottageRepository cottageRepository;
    private final CottageOwnerRepository cottageOwnerRepository;
    private final DataConverter converter;
    private final RuleOfConductService ruleOfConductService;
    private final ImageService imageService;
    private final UtilityService utilityService;

    @Override
    public List<Cottage> findAll() {
        return cottageRepository.findAll();
    }

    @Override
    public CottageDto findById(long id) {
        return converter.convert(cottageRepository.getById(id), CottageDto.class);
    }

    @Override
    public Cottage get(Long id) {
        return cottageRepository.get(id);
    }

    @Override
    public List<CottageDto> findByCottageOwnerId(long id) {
        return converter.convert(cottageRepository.findByCottageOwnerId(id), CottageDto.class);
    }

    @Override
    public Long create(CottageCreationDto cottageDto) {
        Cottage cottage;
        if (cottageDto.getId() == -1) {
            cottage = new Cottage();
        } else {
            cottage = cottageRepository.getById(cottageDto.getId());
        }

        CottageOwner cottageOwner = cottageOwnerRepository.getById(cottageDto.getOwnerId());
        Address address = new Address();
        cottage.setName(cottageDto.getName());
        address.setCity(cottageDto.getCity());
        address.setCountry(cottageDto.getCountry());
        address.setStreet(cottageDto.getStreet());
        cottage.setAddress(address);
        cottage.setPrice(cottageDto.getPrice());
        cottage.setCottageOwner(cottageOwner);
        List<RuleOfConduct> rules = ruleOfConductService.createRulesFromString(cottageDto.getRules());
        cottage.setRules(new HashSet<>(rules));
        cottage.setDescription(cottageDto.getDescription());
        cottage.setGuestLimit(cottageDto.getGuestLimit());
        try {
            cottage.setImages(imageService.saveImages(cottageDto.getImages()));
        } catch (IOException e) {
            e.printStackTrace();
        }
        Cottage savedCottage = cottageRepository.save(cottage);
        savedCottage.setRooms(convertDtoToModel(cottageDto.getRooms(), savedCottage));
        Set<CottageUtility> utilities = utilityService.convertStringToUtility(cottageDto.getAdditionalServices(), savedCottage);
        savedCottage.setUtilities(utilities);
        return cottageRepository.save(savedCottage).getId();
    }

    @Override
    public List<Cottage> filterByDate(LocalDate startDate, LocalDate endDate) {
        List<Cottage> cottages = new ArrayList<>();
        for (Cottage cottage : findAll()) {
            for (AvailableTimePeriod period : cottage.getAvailableTimePeriods()) {
                if (period.getStartDate().isBefore(startDate) && period.getEndDate().isAfter(endDate)) {
                    cottages.add(cottage);
                    break;
                }
            }
        }
        return cottages;
    }

    @Override
    public List<Cottage> findFiltered(EntitySearchDto filterDto) {
        List<Cottage> cottages = new ArrayList<>();

        for (Cottage cottage : filterByDate(filterDto.startDate, filterDto.endDate)) {
            if (cottage.getGuestLimit() >= filterDto.people && (cottage.getAddress().getCountry().equals(filterDto.country) || filterDto.country.equals("")))
                cottages.add(cottage);
        }

        return cottages;
    }

    private Set<Room> convertDtoToModel(List<RoomDto> rooms, Cottage cottage) {
        return rooms.stream().map(roomDto -> createRoom(roomDto, cottage)).collect(Collectors.toSet());
    }

    private Room createRoom(RoomDto roomDto, Cottage cottage) {
        if (roomDto.getId() == -1) {
            return new Room(roomDto.getNumberOfBeds(), cottage);
        } else {
            Room room = new Room();
            room.setId(roomDto.getId());
            room.setNumberOfBeds(roomDto.getNumberOfBeds());
            room.setCottage(cottage);
            return room;
        }
    }

    @Override
    public Boolean delete(Long id) {
        Cottage cottage = cottageRepository.getById(id);
        if ((cottage.getCottageReservations() == null || cottage.getCottageReservations().size() == 0)
                && (cottage.getCottageQuickReservations() == null || cottage.getCottageQuickReservations().size() == 0)) {
            cottageRepository.deleteById(id);
            return true;
        }
        return false;
    }
}
