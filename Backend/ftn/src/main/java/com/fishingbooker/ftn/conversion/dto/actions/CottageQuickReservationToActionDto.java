package com.fishingbooker.ftn.conversion.dto.actions;

import com.fishingbooker.ftn.bom.EntityType;
import com.fishingbooker.ftn.bom.adventures.QuickReservationUtility;
import com.fishingbooker.ftn.bom.cottages.CottageQuickReservation;
import com.fishingbooker.ftn.dto.ActionDto;
import com.fishingbooker.ftn.dto.UtilityDto;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

@Component
public class CottageQuickReservationToActionDto implements Converter<CottageQuickReservation, ActionDto> {

    @Override
    public ActionDto convert(CottageQuickReservation source) {
        ActionDto dto = new ActionDto();
        dto.setId(source.getId());
        dto.setEntityName(source.getCottage().getName());
        dto.setEntityId(source.getCottage().getId());
        dto.setEntityType(EntityType.COTTAGE);
        dto.setPrice(source.getPrice());
        dto.setRating(source.getCottage().getRating());
        dto.setOwnerName(source.getCottage().getCottageOwner().getFullName());
        dto.setGuestLimit(source.getGuestLimit());
        dto.setUtilities(getUtilitiesFromQuickReservation(source.getUtilities()));
        dto.setAddress(source.getCottage().getAddress().toString());
        dto.setDescription(source.getCottage().getDescription());
        dto.setImageUrls(source.getCottage().getImages().stream().map(image -> image.getUrl()).collect(Collectors.toSet()));
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
