package com.fishingbooker.ftn.service.interfaces;

import com.fishingbooker.ftn.bom.users.ApplicationUser;
import com.fishingbooker.ftn.dto.ApplicationUserDto;

import java.util.List;

public interface ApplicationUserService {

    List<ApplicationUser> findAll();

    ApplicationUser findById(Long id);

    ApplicationUser findByEmail(String email);

    ApplicationUser create(ApplicationUserDto userDto);

    ApplicationUser update(ApplicationUser user);

    void delete(Long id);

    Boolean verifyUser(String token) throws Exception;

}
