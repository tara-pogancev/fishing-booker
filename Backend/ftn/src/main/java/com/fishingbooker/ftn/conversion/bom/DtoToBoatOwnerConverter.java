package com.fishingbooker.ftn.conversion.bom;

import com.fishingbooker.ftn.bom.Address;
import com.fishingbooker.ftn.bom.users.ApplicationRole;
import com.fishingbooker.ftn.bom.users.BoatOwner;
import com.fishingbooker.ftn.conversion.DataConverter;
import com.fishingbooker.ftn.dto.ApplicationUserDto;
import lombok.RequiredArgsConstructor;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class DtoToBoatOwnerConverter implements Converter<ApplicationUserDto, BoatOwner> {
    private final DataConverter converter;

    @Override
    public BoatOwner convert(ApplicationUserDto source) {
        BoatOwner boatOwner = new BoatOwner();
        boatOwner.setId(source.getId());
        boatOwner.setPassword(source.getPassword());
        boatOwner.setName(source.getName());
        boatOwner.setLastName(source.getLastName());
        boatOwner.setEmail(source.getEmail());
        boatOwner.setPhone(source.getPhone());
        boatOwner.setEnabled(source.getEnabled());
        boatOwner.setRole(ApplicationRole.getRoleFromString(source.getRole()));
        boatOwner.setUserAddress(converter.convert(source, Address.class));
        return boatOwner;
    }

}
