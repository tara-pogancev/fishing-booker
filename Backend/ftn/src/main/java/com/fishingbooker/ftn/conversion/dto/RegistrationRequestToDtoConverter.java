package com.fishingbooker.ftn.conversion.dto;

import com.fishingbooker.ftn.bom.users.ApplicationRole;
import com.fishingbooker.ftn.bom.users.RegistrationRequest;
import com.fishingbooker.ftn.dto.RegistrationRequestDto;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Component
public class RegistrationRequestToDtoConverter implements Converter<RegistrationRequest, RegistrationRequestDto> {
    @Override
    public RegistrationRequestDto convert(RegistrationRequest source) {
        RegistrationRequestDto dto = new RegistrationRequestDto();
        dto.setName(source.getUser().getName());
        dto.setLastName(source.getUser().getLastName());
        dto.setMail(source.getUser().getEmail());
        dto.setUserType(ApplicationRole.toString(source.getUser().getRole()));
        dto.setRegistrationDescription(source.getRegistrationDescription());
        dto.setId(source.getId());
        return dto;
    }
}
