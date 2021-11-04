package com.fishingbooker.ftn.service;


import com.fishingbooker.ftn.bom.users.CottageOwner;
import com.fishingbooker.ftn.conversion.DataConverter;
import com.fishingbooker.ftn.dto.ApplicationUserDto;
import com.fishingbooker.ftn.repository.CottageOwnerRepository;
import com.fishingbooker.ftn.service.interfaces.CottageOwnerService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
@Transactional
@RequiredArgsConstructor
public class CottageOwnerServiceImpl implements CottageOwnerService {

    private final DataConverter converter;
    private final CottageOwnerRepository cottageOwnerRepository;

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
        return cottageOwnerRepository.save(cottageOwner);
    }
}
