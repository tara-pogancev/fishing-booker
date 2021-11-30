package com.fishingbooker.ftn.service;

import com.fishingbooker.ftn.bom.SystemProperties;
import com.fishingbooker.ftn.repository.SystemPropertiesRepository;
import com.fishingbooker.ftn.service.interfaces.SystemPropertiesService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
@Transactional
@RequiredArgsConstructor
public class SystemPropertiesServiceImpl implements SystemPropertiesService {

    private final SystemPropertiesRepository systemPropertiesRepository;

    @Override
    public SystemProperties get() {
        return systemPropertiesRepository.findAll().get(0);
    }

    public void updateComissionPrecentage(Double incommingPrecentage){
        SystemProperties properties=this.get();
        properties.setIncomePercentage(incommingPrecentage);
        systemPropertiesRepository.save(properties);
    }
}
