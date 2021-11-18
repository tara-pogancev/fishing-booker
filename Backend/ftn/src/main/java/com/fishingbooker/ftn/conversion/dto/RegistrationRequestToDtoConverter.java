package com.fishingbooker.ftn.conversion.dto;

import com.fishingbooker.ftn.bom.RegistrationRequest;
import com.fishingbooker.ftn.bom.users.ApplicationRole;
import com.fishingbooker.ftn.dto.RegistrationRequestDto;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Component
public class RegistrationRequestToDtoConverter implements Converter<RegistrationRequest, RegistrationRequestDto> {
    @Override
    public RegistrationRequestDto convert(RegistrationRequest source) {
        RegistrationRequestDto dto=new RegistrationRequestDto();
        dto.name=source.getUser().getName();
        dto.lastName=source.getUser().getLastName();
        dto.mail=source.getUser().getEmail();
        dto.userType= ApplicationRole.toString(source.getUser().getRole());
        dto.registrationDescription=source.getRegistrationDescription();
        return dto;
    }
}
