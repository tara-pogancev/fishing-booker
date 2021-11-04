package com.fishingbooker.ftn.conversion.bom;

import com.fishingbooker.ftn.bom.users.ApplicationRole;
import com.fishingbooker.ftn.bom.users.FishingInstructor;
import com.fishingbooker.ftn.dto.ApplicationUserDto;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Component
public class DtoToFishingInstructorConverter implements Converter<ApplicationUserDto, FishingInstructor> {

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
        // todo: Address
        return fishingInstructor;
    }

}
