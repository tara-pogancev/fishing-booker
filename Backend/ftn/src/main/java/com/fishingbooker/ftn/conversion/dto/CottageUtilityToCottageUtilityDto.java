package com.fishingbooker.ftn.conversion.dto;

import com.fishingbooker.ftn.bom.cottages.CottageUtility;
import com.fishingbooker.ftn.dto.CottageUtilityDto;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Component
public class CottageUtilityToCottageUtilityDto implements Converter<CottageUtility, CottageUtilityDto> {
    @Override
    public CottageUtilityDto convert(CottageUtility source) {
        CottageUtilityDto dto = new CottageUtilityDto();
        dto.setId(source.getId());
        dto.setName(source.getUtility().getName());
        dto.setUtilityId(source.getUtility().getId());
        dto.setPrice(source.getPrice());
        return dto;
    }
}
