package com.fishingbooker.ftn.conversion.dto;

import com.fishingbooker.ftn.bom.users.ApplicationRole;
import com.fishingbooker.ftn.bom.users.ApplicationUser;
import com.fishingbooker.ftn.bom.users.RegisteredClient;
import com.fishingbooker.ftn.dto.ApplicationUserDto;
import com.fishingbooker.ftn.dto.RegisteredClientDto;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Component
public class ClientToDtoConverter implements Converter<RegisteredClient, RegisteredClientDto> {

    @Override
    public RegisteredClientDto convert(RegisteredClient source) {
        RegisteredClientDto dto = new RegisteredClientDto();
        dto.setId(source.getId());
        dto.setPassword(source.getPassword());
        dto.setName(source.getName());
        dto.setLastName(source.getLastName());
        dto.setEmail(source.getEmail());
        dto.setFullAddress(source.getUserAddress().toString());
        dto.setPhone(source.getPhone());
        dto.setRole(ApplicationRole.toString(source.getRole()));
        dto.setEnabled(source.getEnabled());
        dto.setPenalties(source.getPenalties());
        dto.setIsBlocked(source.getIsBlocked());
        dto.setStreet(source.getUserAddress().getStreet());
        dto.setCountry(source.getUserAddress().getCountry());
        dto.setCity(source.getUserAddress().getCity());
        return dto;
    }

}

