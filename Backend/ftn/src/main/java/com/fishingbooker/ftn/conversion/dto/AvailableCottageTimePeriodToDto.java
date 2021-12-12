package com.fishingbooker.ftn.conversion.dto;

import com.fishingbooker.ftn.bom.cottages.AvailableCottageTimePeriod;
import com.fishingbooker.ftn.dto.AvailableCottageTimePeriodDto;
import lombok.RequiredArgsConstructor;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class AvailableCottageTimePeriodToDto implements Converter<AvailableCottageTimePeriod, AvailableCottageTimePeriodDto> {
    @Override
    public AvailableCottageTimePeriodDto convert(AvailableCottageTimePeriod source) {
        AvailableCottageTimePeriodDto dto = new AvailableCottageTimePeriodDto();
        dto.setCottageId(source.getCottage().getId());
        dto.setStartDate(source.getStartDate());
        dto.setEndDate(source.getEndDate());

        return dto;
    }
}
