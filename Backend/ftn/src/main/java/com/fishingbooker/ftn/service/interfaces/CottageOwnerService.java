package com.fishingbooker.ftn.service.interfaces;

import com.fishingbooker.ftn.bom.users.CottageOwner;
import com.fishingbooker.ftn.dto.ApplicationUserDto;

import java.util.List;

public interface CottageOwnerService {

    List<CottageOwner> findAll();

    CottageOwner findById(Long id);

    CottageOwner create(ApplicationUserDto userDto);

}
