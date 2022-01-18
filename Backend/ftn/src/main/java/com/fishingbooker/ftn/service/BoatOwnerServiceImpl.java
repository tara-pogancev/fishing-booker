package com.fishingbooker.ftn.service;

import com.fishingbooker.ftn.bom.Address;
import com.fishingbooker.ftn.bom.users.BoatOwner;
import com.fishingbooker.ftn.conversion.DataConverter;
import com.fishingbooker.ftn.dto.ApplicationUserDto;
import com.fishingbooker.ftn.dto.BoatOwnerDto;
import com.fishingbooker.ftn.repository.AddressRepository;
import com.fishingbooker.ftn.repository.BoatOwnerRepository;
import com.fishingbooker.ftn.service.interfaces.BoatOwnerService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
@Transactional
@RequiredArgsConstructor
public class BoatOwnerServiceImpl implements BoatOwnerService {

    private final BoatOwnerRepository boatOwnerRepository;
    private final AddressRepository addressRepository;
    private final DataConverter converter;

    @Override
    public List<BoatOwner> findAll() {
        return boatOwnerRepository.findAll();
    }

    @Override
    public BoatOwner findById(Long id) {
        return boatOwnerRepository.get(id);
    }

    @Override
    public BoatOwner create(ApplicationUserDto userDto) {
        BoatOwner boatOwner = converter.convert(userDto, BoatOwner.class);
        return boatOwnerRepository.save(boatOwner);
    }

    @Override
    public List<BoatOwner> getRegisteredBoatOwners() {
        return boatOwnerRepository.getRegisteredBoatOwners();
    }

    @Override
    public Long delete(Long id) {
        BoatOwner boatOwner = boatOwnerRepository.getById(id);
        boatOwner.setDeleted(true);
        boatOwnerRepository.save(boatOwner);
        return boatOwner.getId();
    }

    @Override
    public void update(BoatOwnerDto boatOwnerDto) {
        BoatOwner boatOwner = boatOwnerRepository.get(boatOwnerDto.getId());
        boatOwner.setPassword(new BCryptPasswordEncoder().encode(boatOwnerDto.getPassword()));
        boatOwner.setName(boatOwnerDto.getName());
        boatOwner.setLastName(boatOwnerDto.getLastName());
        boatOwner.setPhone(boatOwnerDto.getPhone());
        Address clientAddress = boatOwner.getUserAddress();
        clientAddress.setStreet(boatOwnerDto.getStreet());
        clientAddress.setCountry(boatOwnerDto.getCountry());
        clientAddress.setCity(boatOwnerDto.getCity());
        addressRepository.save(clientAddress);
        boatOwnerRepository.save(boatOwner);
    }

}
