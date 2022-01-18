package com.fishingbooker.ftn.service.interfaces;

import com.fishingbooker.ftn.bom.users.BoatOwner;
import com.fishingbooker.ftn.dto.ApplicationUserDto;
import com.fishingbooker.ftn.dto.BoatOwnerDto;

import java.util.List;

public interface BoatOwnerService {

    List<BoatOwner> findAll();

    BoatOwner findById(Long id);

    BoatOwner create(ApplicationUserDto userDto);

    List<BoatOwner> getRegisteredBoatOwners();

    Long delete(Long id);

    void update(BoatOwnerDto boatOwnerDto);
}
