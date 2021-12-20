package com.fishingbooker.ftn.service;

import com.fishingbooker.ftn.bom.Address;
import com.fishingbooker.ftn.conversion.DataConverter;
import com.fishingbooker.ftn.dto.ApplicationUserDto;
import com.fishingbooker.ftn.repository.AddressRepository;
import com.fishingbooker.ftn.service.interfaces.AddressService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Service
@RequiredArgsConstructor
public class AddressServiceImpl implements AddressService {

    private final DataConverter converter;
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
    public Address create(ApplicationUserDto dto) {
        Address address = converter.convert(dto, Address.class);
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

    @Override
    public Set<String> findAllCountries() {
        Set<String> countries = new HashSet<>();
        for (Address address : findAll()) {
            countries.add(address.getCountry());
        }
        return countries;
    }

}
