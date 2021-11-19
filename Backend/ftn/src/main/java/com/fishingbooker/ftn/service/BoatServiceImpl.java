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
        boat.setDescription("Lorem ipsum dolor sit amet, consectetur adipiscing elit. Sed enim nisl, malesuada at enim ut, vehicula consequat massa. In auctor id est ut semper. Nulla venenatis libero in ex porttitor, at tincidunt massa faucibus. Phasellus tincidunt mauris sed lectus placerat viverra. Aliquam leo urna, malesuada sit amet efficitur id, venenatis ut neque. Morbi et tempus orci, vel posuere dui. Sed placerat sapien aliquet mauris faucibus, a venenatis arcu fermentum. Nunc lacinia magna ligula, a lobortis augue accumsan vitae. Sed non justo auctor, varius tortor molestie, aliquam ligula. Duis mattis nisi neque, vel sollicitudin justo laoreet in.\n" +
                "\n" +
                "Suspendisse potenti. Maecenas augue dolor, faucibus vitae arcu nec, pretium efficitur nisi. Orci varius natoque penatibus et magnis dis parturient montes, nascetur ridiculus mus. Donec eget lorem sed sem consequat fringilla a quis magna. Vestibulum vehicula a ante sed sagittis. Interdum et malesuada fames ac ante ipsum primis in faucibus. Suspendisse dignissim ultrices nunc vitae ornare. Suspendisse et urna rutrum, blandit magna ut, pharetra felis. Nulla tempus ex nisi.\n" +
                "\n" +
                "Pellentesque habitant morbi tristique senectus et netus et malesuada fames ac turpis egestas. Vivamus nibh augue, congue sed venenatis nec, vestibulum eu erat. In tempus nunc risus, vitae gravida turpis rhoncus vel. Cras ut eros placerat, tempor tortor a, porttitor augue. Fusce arcu leo, vulputate et odio ut, consectetur feugiat quam. Vivamus laoreet fermentum nulla et imperdiet. Donec feugiat rutrum efficitur. Donec porta velit in augue malesuada pulvinar. Nam eget velit tempus, ullamcorper dui ut, facilisis nunc. Lorem ipsum dolor sit amet, consectetur adipiscing elit.\n" +
                "\n");
        boat.setGuestLimit(20);
        boat.setFishingEquipment("Dobar fishing equipment");
        boat.setBoatOwner(owner);
        boat.setUtilities(utilities);
        boat.setCancellationPercentageKeep(12.1);

        boatRepository.save(boat);

    }
}
