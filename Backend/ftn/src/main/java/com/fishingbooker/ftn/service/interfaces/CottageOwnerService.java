package com.fishingbooker.ftn.service.interfaces;

import com.fishingbooker.ftn.bom.users.CottageOwner;
import com.fishingbooker.ftn.dto.ApplicationUserDto;
import com.fishingbooker.ftn.dto.CottageOwnerDto;

import java.util.List;

public interface CottageOwnerService {

    List<CottageOwner> findAll();

    CottageOwner findById(Long id);

    CottageOwner create(ApplicationUserDto userDto);

    void update(CottageOwnerDto cottageOwnerDto);

    List<CottageOwner> getRegisteredOwners();

    Long delete(Long id);
}
