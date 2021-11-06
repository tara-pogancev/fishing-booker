package com.fishingbooker.ftn.conversion.bom;

import com.fishingbooker.ftn.bom.users.ApplicationRole;
import com.fishingbooker.ftn.bom.users.ApplicationUser;
import com.fishingbooker.ftn.dto.ApplicationUserDto;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Component
public class DtoToApplicationUserConverter implements Converter<ApplicationUserDto, ApplicationUser> {

    @Override
    public ApplicationUser convert(ApplicationUserDto source) {
        ApplicationUser user = new ApplicationUser();
        user.setId(source.getId());
        user.setPassword(source.getPassword());
        user.setName(source.getName());
        user.setLastName(source.getLastName());
        user.setEmail(source.getEmail());
        user.setPhone(source.getPhone());
        user.setEnabled(source.getEnabled());
        user.setRole(ApplicationRole.getRoleFromString(source.getRole()));
        // todo: Address
        return user;
    }

}
