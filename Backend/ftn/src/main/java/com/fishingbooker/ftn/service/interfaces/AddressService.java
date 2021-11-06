package com.fishingbooker.ftn.service.interfaces;

import com.fishingbooker.ftn.bom.Address;
import com.fishingbooker.ftn.dto.ApplicationUserDto;

import java.util.List;

public interface AddressService {

    List<Address> findAll();

    Address findById(Long id);

    Address create(ApplicationUserDto address);

    Address update(Address address);

    void delete(Long id);

}
