package com.fishingbooker.ftn.service;

import com.fishingbooker.ftn.bom.Address;
import com.fishingbooker.ftn.bom.boats.Boat;
import com.fishingbooker.ftn.bom.boats.BoatType;
import com.fishingbooker.ftn.bom.boats.BoatUtility;
import com.fishingbooker.ftn.bom.users.BoatOwner;
import com.fishingbooker.ftn.conversion.DataConverter;
import com.fishingbooker.ftn.dto.BoatDto;
import com.fishingbooker.ftn.repository.AddressRepository;
import com.fishingbooker.ftn.repository.BoatOwnerRepository;
import com.fishingbooker.ftn.repository.BoatRepository;
import com.fishingbooker.ftn.repository.UtilityRepository;
import com.fishingbooker.ftn.service.interfaces.BoatService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.*;

@Service
@Transactional
@RequiredArgsConstructor
public class BoatServiceImpl implements BoatService {

    private final BoatRepository boatRepository;
    private final DataConverter converter;
    private final BoatOwnerRepository boatOwnerRepository;
    private final AddressRepository addressRepository;
    private final UtilityRepository utilityRepository;

    @Override
    public List<BoatDto> findAll() {
        List<Boat> boats = boatRepository.findAll();
        return converter.convert(boats, BoatDto.class);
    }

    @Override
    public BoatDto findById(long id) {
        Boat boat = boatRepository.getById(id);
        return converter.convert(boat, BoatDto.class);
    }

    public void initBoats() {
        Boat boat = new Boat();
        Address address = addressRepository.findAll().get(0);

        BoatUtility utility = new BoatUtility();
        utility.setName("Free drinks");
        utility.setPrice(40.0);
        utilityRepository.save(utility);

        Set<BoatUtility> utilities = new HashSet<>();
        utilities.add(utility);

        BoatOwner owner = boatOwnerRepository.findAll().get(0);
        boat.setName("Boat name");
        boat.setBoatType(BoatType.BASS_BOAT);
        boat.setBoatLength(45.5);
        boat.setNumberOfEngines(8);
        boat.setEnginePower(37.0);
        boat.setMaxSpeed(250.3);
        boat.setAddress(address);
        boat.setDescription("Lorem ipsum dolor sit amet, consectetur adipiscing elit. Sed enim nisl, malesuada at enim ut."
                );
        boat.setGuestLimit(20);
        boat.setFishingEquipment("Dobar fishing equipment");
        boat.setBoatOwner(owner);
        boat.setUtilities(utilities);
        boat.setCancellationPercentageKeep(12.1);

        boatRepository.save(boat);

    }
}
