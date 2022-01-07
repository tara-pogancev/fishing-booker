package com.fishingbooker.ftn.conversion.dto.actions;

import com.fishingbooker.ftn.bom.EntityType;
import com.fishingbooker.ftn.bom.adventures.AdventureQuickReservation;
import com.fishingbooker.ftn.bom.adventures.QuickReservationUtility;
import com.fishingbooker.ftn.dto.ActionDto;
import com.fishingbooker.ftn.dto.UtilityDto;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

@Component
public class AdventureQuickReservationToActionDto implements Converter<AdventureQuickReservation, ActionDto> {

    @Override
    public ActionDto convert(AdventureQuickReservation source) {
        ActionDto dto = new ActionDto();
        dto.setId(source.getId());
        dto.setEntityName(source.getAdventure().getName());
        dto.setEntityId(source.getAdventure().getId());
        dto.setEntityType(EntityType.ADVENTURE);
        dto.setPrice(source.getPrice());
        dto.setRating(source.getAdventure().getRating());
        dto.setOwnerName(source.getAdventure().getInstructor().getFullName());
        dto.setGuestLimit(source.getGuestLimit());
        dto.setUtilities(getUtilitiesFromQuickReservation(source.getUtilities()));
        dto.setAddress(source.getAdventure().getAddress().toString());
        dto.setDescription(source.getAdventure().getDescription());
        dto.setImageUrls(source.getAdventure().getImages().stream().map(image -> image.getUrl()).collect(Collectors.toSet()));
        dto.setStartDate(source.getActionStart());
        dto.setEndDate(source.getActionEnd());
        return dto;
    }

    private Set<UtilityDto> getUtilitiesFromQuickReservation(Set<QuickReservationUtility> utilities) {
        Set<UtilityDto> retVal = new HashSet<>();
        for (QuickReservationUtility utility : utilities) {
            UtilityDto dto = new UtilityDto();
            dto.setName(utility.getUtility().getName());
            dto.setPrice(utility.getPrice());
            dto.setId(utility.getId());
            retVal.add(dto);
        }
        return retVal;
    }

}
