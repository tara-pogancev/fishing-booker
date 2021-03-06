package com.fishingbooker.ftn.conversion.dto;

import com.fishingbooker.ftn.bom.boats.Boat;
import com.fishingbooker.ftn.bom.boats.BoatUtility;
import com.fishingbooker.ftn.conversion.DataConverter;
import com.fishingbooker.ftn.dto.BoatDto;
import com.fishingbooker.ftn.dto.UtilityDto;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor
public class BoatToDto implements Converter<Boat, BoatDto> {

    private final ModelMapper modelMapper;

    @Override
    public BoatDto convert(Boat source) {
        BoatDto dto = modelMapper.map(source, BoatDto.class);
        dto.setOwnerName(source.getBoatOwner().getName() + " " + source.getBoatOwner().getLastName());
        dto.setUtilities(getUtilityDtoList(source.getUtilities()));
        dto.setRules(DataConverter.getRules(source.getRules()));
        dto.setNavigationalEquipments(DataConverter.getNavEquipment(source.getNavigationalEquipments()));
        dto.setImageUrls(source.getImages().stream().map(image -> image.getUrl()).collect(Collectors.toSet()));
        return dto;
    }

    private Set<UtilityDto> getUtilityDtoList(Set<BoatUtility> source) {
        Set<UtilityDto> retVal = new HashSet<>();
        for (BoatUtility utility : source) {
            UtilityDto dto = new UtilityDto();
            dto.setName(utility.getUtility().getName());
            dto.setPrice(utility.getPrice());
            dto.setId(utility.getId());
            retVal.add(dto);
        }
        return retVal;
    }

}
