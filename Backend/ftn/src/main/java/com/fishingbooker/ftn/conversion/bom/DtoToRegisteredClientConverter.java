package com.fishingbooker.ftn.conversion.bom;

import com.fishingbooker.ftn.bom.users.ApplicationRole;
import com.fishingbooker.ftn.bom.users.RegisteredClient;
import com.fishingbooker.ftn.dto.ApplicationUserDto;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Component
public class DtoToRegisteredClientConverter implements Converter<ApplicationUserDto, RegisteredClient> {

    @Override
    public RegisteredClient convert(ApplicationUserDto source) {
        RegisteredClient client = new RegisteredClient();
        client.setId(source.getId());
        client.setPassword(source.getPassword());
        client.setName(source.getName());
        client.setLastName(source.getLastName());
        client.setEmail(source.getEmail());
        client.setPhone(source.getPhone());
        client.setEnabled(source.getEnabled());
        client.setRole(getRoleFromString(source.getRole()));
        // todo: Address
        return client;
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
