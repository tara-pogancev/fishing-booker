package com.fishingbooker.ftn.conversion.dto;

import com.fishingbooker.ftn.bom.users.ApplicationRole;
import com.fishingbooker.ftn.bom.users.ApplicationUser;
import com.fishingbooker.ftn.dto.ApplicationUserDto;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Component
public class ApplicationUserToDtoConverter implements Converter<ApplicationUser, ApplicationUserDto> {

    @Override
    public ApplicationUserDto convert(ApplicationUser source) {
        ApplicationUserDto dto = new ApplicationUserDto();
        dto.setId(source.getId());
        dto.setPassword(source.getPassword());
        dto.setName(source.getName());
        dto.setLastName(source.getLastName());
        dto.setEmail(source.getEmail());
        dto.setFullAddress(source.getUserAddress().toString());
        dto.setPhone(source.getPhone());
        dto.setRole(ApplicationRole.toString(source.getRole()));
        dto.setEnabled(source.getEnabled());
        return dto;
    }

}