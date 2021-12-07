package com.fishingbooker.ftn.conversion.dto;

import com.fishingbooker.ftn.bom.adventures.Adventure;
import com.fishingbooker.ftn.conversion.DataConverter;
import com.fishingbooker.ftn.dto.*;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor
public class AdventureToEditAdventureDto implements Converter<Adventure, EditAdventureDto> {

    private final ModelMapper modelMapper;

    @Override
    public EditAdventureDto convert(Adventure source) {
        EditAdventureDto dto=new EditAdventureDto();
        dto.setId(source.getId());
        dto.setName(source.getName());
        dto.setDescription(source.getDescription());
        dto.setStreet(source.getAddress().getStreet());
        dto.setCity(source.getAddress().getCity());
        dto.setCountry(source.getAddress().getCountry());
        dto.setGuestLimit(source.getGuestLimit());
        dto.setPrice(source.getPrice());
        dto.setCancellationFee(source.getCancellationPercentageKeep());
        dto.setFishingEquipment(source.getFishingEquipments().stream().map(fishingEquipment -> new FishingEquipmentDto(fishingEquipment.getId(),fishingEquipment.getFishingEquipmentName())).collect(Collectors.toList()));
        dto.setRules(source.getRules().stream().map(rule-> new RuleDto(rule.getId(),rule.getRuleDescription())).collect(Collectors.toList()));
        dto.setAdditionalServices(source.getUtilities().stream().map(utility->new AdventureUtilityDto(utility.getId(),utility.getUtility().getName(),utility.getPrice(),utility.getUtility().getId())).collect(Collectors.toList()));
        dto.setOwnerId(source.getId());
        dto.setImages(source.getImages().stream().map(image -> new ImageDto(image.getUrl(),"/","exists")).collect(Collectors.toList()));
        return dto;
    }
}
