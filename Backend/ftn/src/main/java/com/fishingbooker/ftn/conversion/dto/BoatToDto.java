package com.fishingbooker.ftn.conversion.dto;

import com.fishingbooker.ftn.bom.Utility;
import com.fishingbooker.ftn.bom.boats.Boat;
import com.fishingbooker.ftn.bom.boats.BoatUtility;
import com.fishingbooker.ftn.dto.BoatDto;
import com.fishingbooker.ftn.dto.UtilityDto;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import java.util.HashSet;
import java.util.Set;

@Component
@RequiredArgsConstructor
public class BoatToDto implements Converter<Boat, BoatDto> {

    private final ModelMapper modelMapper;

    @Override
    public BoatDto convert(Boat source) {
        BoatDto dto = modelMapper.map(source, BoatDto.class);
        dto.setOwnerName(source.getBoatOwner().getName() + " " + source.getBoatOwner().getLastName());
        dto.setUtilities(getUtilityDtoList(source.getUtilities()));
        return dto;
    }

    private Set<UtilityDto> getUtilityDtoList(Set<BoatUtility> source) {
        Set<UtilityDto> retVal = new HashSet<>();
        for (Utility utility: source) {
            retVal.add(modelMapper.map(utility, UtilityDto.class));
        }
        return retVal;
    }
}
