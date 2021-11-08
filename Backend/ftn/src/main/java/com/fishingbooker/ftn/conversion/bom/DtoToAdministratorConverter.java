package com.fishingbooker.ftn.conversion.bom;

import com.fishingbooker.ftn.bom.users.Administrator;
import com.fishingbooker.ftn.bom.users.ApplicationRole;
import com.fishingbooker.ftn.dto.ApplicationUserDto;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Component
public class DtoToAdministratorConverter implements Converter<ApplicationUserDto, Administrator> {

    @Override
    public Administrator convert(ApplicationUserDto source) {
        Administrator administrator = new Administrator();
        administrator.setId(source.getId());
        administrator.setPassword(source.getPassword());
        administrator.setName(source.getName());
        administrator.setLastName(source.getLastName());
        administrator.setEmail(source.getEmail());
        administrator.setPhone(source.getPhone());
        administrator.setEnabled(source.getEnabled());
        administrator.setRole(ApplicationRole.getRoleFromString(source.getRole()));
        return administrator;
    }

}
