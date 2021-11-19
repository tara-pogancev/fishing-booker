package com.fishingbooker.ftn.conversion.dto;

import com.fishingbooker.ftn.bom.Utility;
import com.fishingbooker.ftn.dto.UtilityDto;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class UtilityToDto implements Converter<Utility, UtilityDto> {

    private final ModelMapper modelMapper;

    @Override
    public UtilityDto convert(Utility source) {
        return modelMapper.map(source, UtilityDto.class);
    }

}
