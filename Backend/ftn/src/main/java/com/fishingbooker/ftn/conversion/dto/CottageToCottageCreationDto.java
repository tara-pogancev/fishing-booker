package com.fishingbooker.ftn.conversion.dto;

import com.fishingbooker.ftn.bom.cottages.Cottage;
import com.fishingbooker.ftn.dto.*;
import lombok.RequiredArgsConstructor;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor
public class CottageToCottageCreationDto implements Converter<Cottage, CottageCreationDto> {

    @Override
    public CottageCreationDto convert(Cottage source) {
        CottageCreationDto dto = new CottageCreationDto();
        dto.setId(source.getId());
        dto.setName(source.getName());
        dto.setDescription(source.getDescription());
        dto.setStreet(source.getAddress().getStreet());
        dto.setCity(source.getAddress().getCity());
        dto.setCountry(source.getAddress().getCountry());
        dto.setGuestLimit(source.getGuestLimit());
        dto.setPrice(source.getPrice());
        dto.setRooms(source.getRooms().stream().map(room -> new RoomDto(room.getId(), room.getNumberOfBeds())).collect(Collectors.toList()));
        dto.setRules(source.getRules().stream().map(rule -> new RuleDto(rule.getId(), rule.getRuleDescription())).collect(Collectors.toList()));
        dto.setAdditionalServices(source.getUtilities().stream().map(utility -> new UtilityDto(utility.getId(), utility.getUtility().getName(), utility.getPrice())).collect(Collectors.toList()));
        dto.setOwnerId(source.getId());
        dto.setImages(source.getImages().stream().map(image -> new ImageDto(image.getUrl(), "/", "exists")).collect(Collectors.toList()));
        return dto;
    }
}
