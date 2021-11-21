package com.fishingbooker.ftn.service;

import com.fishingbooker.ftn.bom.adventures.Adventure;
import com.fishingbooker.ftn.conversion.DataConverter;
import com.fishingbooker.ftn.dto.AdventureDto;
import com.fishingbooker.ftn.repository.AdventureRepository;
import com.fishingbooker.ftn.service.interfaces.AdventureService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
@Transactional
@RequiredArgsConstructor
public class AdventureServiceImpl implements AdventureService {

    private final DataConverter converter;
    private final AdventureRepository adventureRepository;

    @Override
    public List<AdventureDto> findAll() {
        List<Adventure> adventures = adventureRepository.findAll();
        return converter.convert(adventures, AdventureDto.class);
    }

    @Override
    public AdventureDto findById(long id) {
        Adventure adventure = adventureRepository.getById(id);
        return converter.convert(adventure, AdventureDto.class);
    }

}
