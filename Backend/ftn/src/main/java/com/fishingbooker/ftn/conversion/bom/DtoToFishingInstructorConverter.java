package com.fishingbooker.ftn.conversion.bom;

import com.fishingbooker.ftn.bom.Address;
import com.fishingbooker.ftn.bom.users.ApplicationRole;
import com.fishingbooker.ftn.bom.users.FishingInstructor;
import com.fishingbooker.ftn.conversion.DataConverter;
import com.fishingbooker.ftn.dto.ApplicationUserDto;
import lombok.RequiredArgsConstructor;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class DtoToFishingInstructorConverter implements Converter<ApplicationUserDto, FishingInstructor> {
    private final DataConverter converter;

    @Override
    public FishingInstructor convert(ApplicationUserDto source) {
        FishingInstructor fishingInstructor = new FishingInstructor();
        fishingInstructor.setId(source.getId());
        fishingInstructor.setPassword(source.getPassword());
        fishingInstructor.setName(source.getName());
        fishingInstructor.setLastName(source.getLastName());
        fishingInstructor.setEmail(source.getEmail());
        fishingInstructor.setPhone(source.getPhone());
        fishingInstructor.setEnabled(source.getEnabled());
        fishingInstructor.setRole(ApplicationRole.getRoleFromString(source.getRole()));
        fishingInstructor.setUserAddress(converter.convert(source, Address.class));
        return fishingInstructor;
    }

}
