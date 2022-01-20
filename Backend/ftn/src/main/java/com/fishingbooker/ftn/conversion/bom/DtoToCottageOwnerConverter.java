package com.fishingbooker.ftn.conversion.bom;

import com.fishingbooker.ftn.bom.Address;
import com.fishingbooker.ftn.bom.users.ApplicationRole;
import com.fishingbooker.ftn.bom.users.CottageOwner;
import com.fishingbooker.ftn.conversion.DataConverter;
import com.fishingbooker.ftn.dto.ApplicationUserDto;
import lombok.RequiredArgsConstructor;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class DtoToCottageOwnerConverter implements Converter<ApplicationUserDto, CottageOwner> {
    private final DataConverter converter;

    @Override
    public CottageOwner convert(ApplicationUserDto source) {
        CottageOwner cottageOwner = new CottageOwner();
        cottageOwner.setId(source.getId());
        cottageOwner.setPassword(source.getPassword());
        cottageOwner.setName(source.getName());
        cottageOwner.setLastName(source.getLastName());
        cottageOwner.setEmail(source.getEmail());
        cottageOwner.setPhone(source.getPhone());
        cottageOwner.setEnabled(source.getEnabled());
        cottageOwner.setRole(ApplicationRole.getRoleFromString(source.getRole()));
        cottageOwner.setUserAddress(converter.convert(source, Address.class));
        return cottageOwner;
    }

}
