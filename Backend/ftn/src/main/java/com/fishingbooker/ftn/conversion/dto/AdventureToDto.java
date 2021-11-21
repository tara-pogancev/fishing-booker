package com.fishingbooker.ftn.conversion.dto;

import com.fishingbooker.ftn.bom.Utility;
import com.fishingbooker.ftn.bom.adventures.Adventure;
import com.fishingbooker.ftn.bom.adventures.AdventureUtility;
import com.fishingbooker.ftn.dto.AdventureDto;
import com.fishingbooker.ftn.dto.UtilityDto;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import java.util.HashSet;
import java.util.Set;

@Component
@RequiredArgsConstructor
public class AdventureToDto implements Converter<Adventure, AdventureDto> {

    private final ModelMapper modelMapper;

    @Override
    public AdventureDto convert(Adventure source) {
        AdventureDto dto = modelMapper.map(source, AdventureDto.class);
        dto.setOwnerName(source.getInstructor().getName() + " " + source.getInstructor().getLastName());
        dto.setUtilities(getUtilityDtoList(source.getUtilities()));
        return dto;
    }

    private Set<UtilityDto> getUtilityDtoList(Set<AdventureUtility> source) {
        Set<UtilityDto> retVal = new HashSet<>();
        for (AdventureUtility utility : source) {
            retVal.add(modelMapper.map(utility.getUtility(), UtilityDto.class));
        }
        return retVal;
    }
}
