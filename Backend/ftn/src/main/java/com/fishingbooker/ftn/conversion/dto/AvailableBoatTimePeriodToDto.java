package com.fishingbooker.ftn.conversion.dto;

import com.fishingbooker.ftn.bom.boats.AvailableBoatTimePeriod;
import com.fishingbooker.ftn.dto.AvailableBoatTimePeriodDto;
import lombok.RequiredArgsConstructor;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class AvailableBoatTimePeriodToDto implements Converter<AvailableBoatTimePeriod, AvailableBoatTimePeriodDto> {
    @Override
    public AvailableBoatTimePeriodDto convert(AvailableBoatTimePeriod source) {
        AvailableBoatTimePeriodDto dto = new AvailableBoatTimePeriodDto();
        dto.setBoatId(source.getBoat().getId());
        dto.setStartDate(source.getStartDate());
        dto.setEndDate(source.getEndDate());

        return dto;
    }
}
