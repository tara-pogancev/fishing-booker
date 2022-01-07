package com.fishingbooker.ftn.conversion.dto;

import com.fishingbooker.ftn.bom.adventures.AdventureUtility;
import com.fishingbooker.ftn.dto.AdventureUtilityDto;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Component
public class AdventureUtilityToAdventureUtilityDto implements Converter<AdventureUtility, AdventureUtilityDto> {
    @Override
    public AdventureUtilityDto convert(AdventureUtility source) {
        AdventureUtilityDto dto = new AdventureUtilityDto();
        dto.setId(source.getId());
        dto.setName(source.getUtility().getName());
        dto.setUtilityId(source.getUtility().getId());
        dto.setPrice(source.getPrice());
        return dto;
    }
}
