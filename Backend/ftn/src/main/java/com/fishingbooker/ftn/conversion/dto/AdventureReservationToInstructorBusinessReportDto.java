package com.fishingbooker.ftn.conversion.dto;

import com.fishingbooker.ftn.bom.adventures.AdventureReservation;
import com.fishingbooker.ftn.dto.InstructorBusinessReportDto;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Component
public class AdventureReservationToInstructorBusinessReportDto implements Converter<AdventureReservation, InstructorBusinessReportDto> {
    @Override
    public InstructorBusinessReportDto convert(AdventureReservation source) {
        InstructorBusinessReportDto dto = new InstructorBusinessReportDto();
        dto.setEndDate(source.getReservationEnd());
        dto.setStartDate(source.getReservationStart());
        if (source.getAdventure() != null) {
            dto.setAdventureName(source.getAdventure().getName());
        } else {
            dto.setAdventureName("Deleted");
        }
        dto.setId(source.getId());
        dto.setPrice(source.getPrice());
        return dto;

    }
}
