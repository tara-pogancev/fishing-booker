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
        user.setRole(getRoleFromString(source.getRole()));
        // todo: Address
        return user;
    }

    private ApplicationRole getRoleFromString(String role) {
        switch (role) {
            case "Registered Client":
                return ApplicationRole.REGISTERED_CLIENT;
            case "Boat Owner":
                return ApplicationRole.BOAT_OWNER;
            case "Cottage Owner":
                return ApplicationRole.COTTAGE_OWNER;
            case "Fishing Instructor":
                return ApplicationRole.FISHING_INSTRUCTOR;
            default:
                return ApplicationRole.ADMINISTRATOR;
        }
    }

}
