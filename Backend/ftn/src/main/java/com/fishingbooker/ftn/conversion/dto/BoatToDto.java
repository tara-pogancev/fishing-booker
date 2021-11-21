package com.fishingbooker.ftn.conversion.dto;

import com.fishingbooker.ftn.bom.RuleOfConduct;
import com.fishingbooker.ftn.bom.Utility;
import com.fishingbooker.ftn.bom.boats.Boat;
import com.fishingbooker.ftn.bom.boats.BoatUtility;
import com.fishingbooker.ftn.bom.boats.NavigationalEquipment;
import com.fishingbooker.ftn.bom.boats.NavigationalEquipmentEnum;
import com.fishingbooker.ftn.dto.BoatDto;
import com.fishingbooker.ftn.dto.UtilityDto;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
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
        dto.setRules(getRules(source.getRules()));
        dto.setNavigationalEquipments(getNavEquipment(source.getNavigationalEquipments()));
        return dto;
    }

    private Set<UtilityDto> getUtilityDtoList(Set<BoatUtility> source) {
        Set<UtilityDto> retVal = new HashSet<>();
        for (BoatUtility utility : source) {
            UtilityDto dto = new UtilityDto();
            dto.setName(utility.getUtility().getName());
            dto.setPrice(utility.getPrice());
            retVal.add(dto);
        }
        return retVal;
    }

    private Set<String> getNavEquipment(Set<NavigationalEquipment> equipments) {
        Set<String> retVal = new HashSet<>();
        for (NavigationalEquipment nav : equipments) {
            retVal.add(nav.getName().toString());
        }
        return retVal;
    }

    private Set<String> getRules(Set<RuleOfConduct> rules) {
        Set<String> retVal = new HashSet<>();
        for (RuleOfConduct rule : rules) {
            retVal.add(rule.getRuleDescription());
        }
        return retVal;
    }
}
