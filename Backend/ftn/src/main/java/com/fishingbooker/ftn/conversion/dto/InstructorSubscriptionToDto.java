package com.fishingbooker.ftn.conversion.dto;

import com.fishingbooker.ftn.bom.users.FishingInstructor;
import com.fishingbooker.ftn.dto.InstructorSubscriptionDto;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Component
public class InstructorSubscriptionToDto implements Converter<FishingInstructor, InstructorSubscriptionDto> {

    @Override
    public InstructorSubscriptionDto convert(FishingInstructor source) {
        InstructorSubscriptionDto dto = new InstructorSubscriptionDto();
        dto.setId(source.getId());
        dto.setDescription(source.getBiography());
        dto.setName(source.getFullName());
        dto.setOwnerName(source.getFullName());
        return dto;
    }
}
