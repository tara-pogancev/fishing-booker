package com.fishingbooker.ftn.service;

import com.fishingbooker.ftn.bom.Address;
import com.fishingbooker.ftn.repository.AddressRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
@RequiredArgsConstructor
public class AddressServiceImpl implements AddressService {

    private final AddressRepository addressRepository;

    @Override
    @Transactional
    public List<Address> findAll() {
        return addressRepository.findAll();
    }

    @Override
    @Transactional
    public Address findById(Long id) {
        return addressRepository.get(id);
    }

    @Override
    public Address create(Address address) {
        return addressRepository.save(address);
    }

    @Override
    @Transactional
    public Address update(Address address) {
        return addressRepository.save(address);
    }

    @Override
    @Transactional
    public void delete(Long id) {
        addressRepository.deleteById(id);
    }

}
