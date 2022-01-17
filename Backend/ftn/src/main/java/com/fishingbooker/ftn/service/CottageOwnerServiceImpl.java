package com.fishingbooker.ftn.service;


import com.fishingbooker.ftn.bom.Address;
import com.fishingbooker.ftn.bom.cottages.CottageReservation;
import com.fishingbooker.ftn.bom.users.CottageOwner;
import com.fishingbooker.ftn.conversion.DataConverter;
import com.fishingbooker.ftn.dto.ApplicationUserDto;
import com.fishingbooker.ftn.dto.CottageOwnerDto;
import com.fishingbooker.ftn.repository.AddressRepository;
import com.fishingbooker.ftn.repository.CottageOwnerRepository;
import com.fishingbooker.ftn.repository.CottageReservationRepository;
import com.fishingbooker.ftn.service.interfaces.CottageOwnerService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Service
@Transactional
@RequiredArgsConstructor
public class CottageOwnerServiceImpl implements CottageOwnerService {

    private final DataConverter converter;
    private final CottageOwnerRepository cottageOwnerRepository;
    private final AddressRepository addressRepository;
    private final CottageReservationRepository cottageReservationRepository;

    @Override
    public List<CottageOwner> findAll() {
        return cottageOwnerRepository.findAll();
    }

    @Override
    public CottageOwner findById(Long id) {
        return cottageOwnerRepository.get(id);
    }

    @Override
    public CottageOwner create(ApplicationUserDto userDto) {
        CottageOwner cottageOwner = converter.convert(userDto, CottageOwner.class);
        cottageOwner.setPassword(new BCryptPasswordEncoder().encode(userDto.getPassword()));
        return cottageOwnerRepository.save(cottageOwner);
    }

    @Override
    public void update(CottageOwnerDto cottageOwnerDto) {
        CottageOwner cottageOwner = cottageOwnerRepository.get(cottageOwnerDto.getId());
        cottageOwner.setPassword(new BCryptPasswordEncoder().encode(cottageOwnerDto.getPassword()));
        cottageOwner.setName(cottageOwnerDto.getName());
        cottageOwner.setLastName(cottageOwnerDto.getLastName());
        cottageOwner.setPhone(cottageOwnerDto.getPhone());
        Address clientAddress = cottageOwner.getUserAddress();
        clientAddress.setStreet(cottageOwnerDto.getStreet());
        clientAddress.setCountry(cottageOwnerDto.getCountry());
        clientAddress.setCity(cottageOwnerDto.getCity());
        addressRepository.save(clientAddress);
        cottageOwnerRepository.save(cottageOwner);
    }

    @Override
    public List<CottageOwner> getRegisteredOwners() {
        return cottageOwnerRepository.getRegisteredOwners();
    }

    @Override
    public Long delete(Long id) {
        CottageOwner cottageOwner = cottageOwnerRepository.getById(id);
        cottageOwner.setDeleted(true);
        cottageOwnerRepository.save(cottageOwner);
        return cottageOwner.getId();
    }

    @Override
    public List<CottageReservation> getPastAdventureReservations(Long id) {
        CottageOwner cottageOwner = cottageOwnerRepository.get(id);
        List<CottageReservation> reservations = new ArrayList<>();
        if (cottageOwner != null) {
            for (CottageReservation cottageReservation : cottageReservationRepository.getCottageReservationsByCottageOwner(id))
                if (cottageReservation.getReservationStart().isBefore(LocalDateTime.now()) && !cottageReservation.getIsCanceled())
                    reservations.add(cottageReservation);
        }
        return reservations;
    }
}
