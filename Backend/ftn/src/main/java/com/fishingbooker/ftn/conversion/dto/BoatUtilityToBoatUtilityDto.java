package com.fishingbooker.ftn.conversion.dto;

import com.fishingbooker.ftn.bom.boats.BoatUtility;
import com.fishingbooker.ftn.dto.BoatUtilityDto;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Component
public class BoatUtilityToBoatUtilityDto  implements Converter<BoatUtility, BoatUtilityDto> {
    @Override
    public BoatUtilityDto convert(BoatUtility source) {
        BoatUtilityDto dto = new BoatUtilityDto();
        dto.setId(source.getId());
        dto.setName(source.getUtility().getName());
        dto.setUtilityId(source.getUtility().getId());
        dto.setPrice(source.getPrice());
        return dto;
    }
}