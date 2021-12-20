package com.fishingbooker.ftn.conversion.dto;

import com.fishingbooker.ftn.bom.AvailableTimePeriod;
import com.fishingbooker.ftn.bom.adventures.Adventure;
import com.fishingbooker.ftn.bom.adventures.AdventureUtility;
import com.fishingbooker.ftn.bom.users.FishingInstructor;
import com.fishingbooker.ftn.conversion.DataConverter;
import com.fishingbooker.ftn.dto.AdventureDto;
import com.fishingbooker.ftn.dto.FishingEquipmentDto;
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
public class AdventureToDto implements Converter<Adventure, AdventureDto> {

    private final ModelMapper modelMapper;

    @Override
    public AdventureDto convert(Adventure source) {
        AdventureDto dto = modelMapper.map(source, AdventureDto.class);
        dto.setOwnerName(source.getInstructor().getName() + " " + source.getInstructor().getLastName());
        dto.setUtilities(getUtilityDtoList(source.getUtilities()));
        dto.setRules(DataConverter.getRules(source.getRules()));
        dto.setNavigationalEquipments(source.getFishingEquipments().stream().map(fishingEquipment -> fishingEquipment.getFishingEquipmentName()).collect(Collectors.toSet()));
        dto.setImageUrls(source.getImages().stream().map(image -> image.getUrl()).collect(Collectors.toSet()));
        dto.setInstructorBiography(source.getInstructor().getBiography());
        dto.setFishingEquipments(source.getFishingEquipments().stream().map(fishingEquipment -> new FishingEquipmentDto(fishingEquipment.getId(),fishingEquipment.getFishingEquipmentName())).collect(Collectors.toList()));
        dto.setAvailableTimePeriods(getAvailableTimePeriods(source.getInstructor()));
        return dto;
    }

    private Set<AvailableTimePeriod> getAvailableTimePeriods(FishingInstructor instructor) {
        Set<AvailableTimePeriod> periods = new HashSet<>();
        for (AvailableTimePeriod period : instructor.getAvailableTimePeriods()) {
            periods.add(period);
        }
        return periods;
    }

    private Set<UtilityDto> getUtilityDtoList(Set<AdventureUtility> source) {
        Set<UtilityDto> retVal = new HashSet<>();
        for (AdventureUtility utility : source) {
            UtilityDto dto = new UtilityDto();
            dto.setName(utility.getUtility().getName());
            dto.setPrice(utility.getPrice());
            retVal.add(dto);
        }
        return retVal;
    }

}
