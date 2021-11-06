package com.fishingbooker.ftn.conversion.bom;

import com.fishingbooker.ftn.bom.Address;
import com.fishingbooker.ftn.bom.users.Administrator;
import com.fishingbooker.ftn.dto.ApplicationUserDto;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Component
public class DtoToAddressConverter implements Converter<ApplicationUserDto, Address> {

    @Override
    public Address convert(ApplicationUserDto source) {
        Address address = new Address();
        address.setCountry(source.getCountry());
        address.setCity(source.getCity());
        address.setStreet(source.getStreet());
        return address;
    }

}
