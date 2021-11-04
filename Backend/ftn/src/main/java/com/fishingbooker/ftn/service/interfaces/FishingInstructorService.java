package com.fishingbooker.ftn.service.interfaces;

import com.fishingbooker.ftn.bom.users.FishingInstructor;
import com.fishingbooker.ftn.dto.ApplicationUserDto;

import java.util.List;

public interface FishingInstructorService {

    List<FishingInstructor> findAll();

    FishingInstructor findById(Long id);

    FishingInstructor create(ApplicationUserDto userDto);

}
