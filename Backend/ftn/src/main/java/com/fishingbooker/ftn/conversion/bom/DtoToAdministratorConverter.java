package com.fishingbooker.ftn.conversion.bom;

import com.fishingbooker.ftn.bom.Address;
import com.fishingbooker.ftn.bom.users.Administrator;
import com.fishingbooker.ftn.bom.users.ApplicationRole;
import com.fishingbooker.ftn.dto.AdministratorDto;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Component
public class DtoToAdministratorConverter implements Converter<AdministratorDto, Administrator> {

    @Override
    public Administrator convert(AdministratorDto source) {
        Administrator administrator = new Administrator();
        administrator.setId(source.getId());
        administrator.setPassword(source.getPassword());
        administrator.setName(source.getName());
        administrator.setLastName(source.getLastName());
        administrator.setEmail(source.getEmail());
        administrator.setPhone(source.getPhone());
        administrator.setEnabled(source.getEnabled());
        administrator.setRole(ApplicationRole.getRoleFromString(source.getRole()));
        Address address = new Address();
        address.setCountry(source.getCountry());
        address.setCity(source.getCity());
        address.setStreet(source.getStreet());
        administrator.setUserAddress(address);
        administrator.setFirstTimeLoggedIn(source.getFirstTimeLoggedIn());
        return administrator;
    }

}
