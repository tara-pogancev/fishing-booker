package com.fishingbooker.ftn.service;

import com.fishingbooker.ftn.bom.Address;

import java.util.List;

public interface AddressService {

    List<Address> findAll();

    Address findById(Long id);

    Address create(Address address);

    Address update(Address address);

    void delete(Long id);

}
