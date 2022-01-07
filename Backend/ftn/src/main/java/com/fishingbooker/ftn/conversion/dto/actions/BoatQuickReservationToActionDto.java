package com.fishingbooker.ftn.conversion.dto.actions;

import com.fishingbooker.ftn.bom.EntityType;
import com.fishingbooker.ftn.bom.adventures.QuickReservationUtility;
import com.fishingbooker.ftn.bom.boats.BoatQuickReservation;
import com.fishingbooker.ftn.dto.ActionDto;
import com.fishingbooker.ftn.dto.UtilityDto;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

@Component
public class BoatQuickReservationToActionDto implements Converter<BoatQuickReservation, ActionDto> {

    @Override
    public ActionDto convert(BoatQuickReservation source) {
        ActionDto dto = new ActionDto();
        dto.setId(source.getId());
        dto.setEntityName(source.getBoat().getName());
        dto.setEntityId(source.getBoat().getId());
        dto.setEntityType(EntityType.BOAT);
        dto.setPrice(source.getPrice());
        dto.setRating(source.getBoat().getRating());
        dto.setOwnerName(source.getBoat().getBoatOwner().getFullName());
        dto.setGuestLimit(source.getGuestLimit());
        dto.setUtilities(getUtilitiesFromQuickReservation(source.getUtilities()));
        dto.setAddress(source.getBoat().getAddress().toString());
        dto.setDescription(source.getBoat().getDescription());
        dto.setImageUrls(source.getBoat().getImages().stream().map(image -> image.getUrl()).collect(Collectors.toSet()));
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
