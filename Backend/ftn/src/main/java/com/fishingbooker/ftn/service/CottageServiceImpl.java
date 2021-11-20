package com.fishingbooker.ftn.service;

import com.fishingbooker.ftn.bom.Address;
import com.fishingbooker.ftn.bom.cottages.Cottage;
import com.fishingbooker.ftn.bom.cottages.CottageUtility;
import com.fishingbooker.ftn.bom.cottages.Room;
import com.fishingbooker.ftn.bom.users.CottageOwner;
import com.fishingbooker.ftn.conversion.DataConverter;
import com.fishingbooker.ftn.dto.CottageDto;
import com.fishingbooker.ftn.repository.*;
import com.fishingbooker.ftn.service.interfaces.CottageService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Service
@Transactional
@RequiredArgsConstructor
public class CottageServiceImpl implements CottageService {

    private final CottageRepository cottageRepository;
    private final RoomRepository roomRepository;
    private final AddressRepository addressRepository;
    private final CottageOwnerRepository cottageOwnerRepository;
    private final CottageUtilityRepository utilityRepository;
    private final DataConverter converter;

    @Override
    public List<CottageDto> findAll() {
        List<Cottage> list = cottageRepository.findAll();
        return converter.convert(list, CottageDto.class);
    }

    @Override
    public CottageDto findById(long id) {
        return converter.convert(cottageRepository.getById(id), CottageDto.class);
    }

    @Override
    public void initCottages() {
//        CottageOwner owner = cottageOwnerRepository.findAll().get(0);
//
//        Address address = new Address();
//        address.setStreet("R. Pte. do Arquinho");
//        address.setCity("Porto");
//        address.setCountry("Portugal");
//        address = addressRepository.save(address);
//
//        CottageUtility utility1 = new CottageUtility();
//        utility1.setName("Pets Allowed");
//        utility1.setPrice(0.0);
//        utility1 = utilityRepository.save(utility1);
//
//        CottageUtility utility2 = new CottageUtility();
//        utility2.setName("Bar");
//        utility2.setPrice(35.5);
//        utility2 = utilityRepository.save(utility2);
//
//        Set<CottageUtility> utilities = new HashSet<>();
//        utilities.add(utility1);
//        utilities.add(utility2);

        Cottage cottage = cottageRepository.getById(4L);
        Room room = new Room();
        room.setNumberOfBeds(55);
        room.setCottage(cottage);
        room = roomRepository.save(room);
//        cottage.getRooms().add(room);
//        cottageRepository.save(cottage);

//        Cottage cottage = new Cottage();
//        cottage.setCottageOwner(owner);
//        cottage.setGuestLimit(8);
//        cottage.setPrice(45.0);
//        cottage.setName("Parque do Arquinho II");
//        cottage.setAddress(address);
//        cottage.setRooms(rooms);
//        cottage.setDescription("Lorem ipsum ipsum ipsum!");
//        cottage.setUtilities(utilities);
//        cottageRepository.save(cottage);

    }
}
