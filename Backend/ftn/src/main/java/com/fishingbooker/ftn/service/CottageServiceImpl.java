package com.fishingbooker.ftn.service;

import com.fishingbooker.ftn.bom.cottages.Cottage;
import com.fishingbooker.ftn.bom.cottages.Room;
import com.fishingbooker.ftn.conversion.DataConverter;
import com.fishingbooker.ftn.dto.CottageDto;
import com.fishingbooker.ftn.repository.*;
import com.fishingbooker.ftn.service.interfaces.CottageService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
@Transactional
@RequiredArgsConstructor
public class CottageServiceImpl implements CottageService {

    private final CottageRepository cottageRepository;
    private final RoomRepository roomRepository;
    private final AddressRepository addressRepository;
    private final CottageOwnerRepository cottageOwnerRepository;
    private final CottageUtilityRepository utilityRepository;
    private final DataConverter converter;

    @Override
    public List<Cottage> findAll() {
        return cottageRepository.findAll();

    }

    @Override
    public CottageDto findById(long id) {
        return converter.convert(cottageRepository.getById(id), CottageDto.class);
    }

    @Override
    public Long delete(Long id) {
        cottageRepository.deleteById(id);
        return id;
    }

}
