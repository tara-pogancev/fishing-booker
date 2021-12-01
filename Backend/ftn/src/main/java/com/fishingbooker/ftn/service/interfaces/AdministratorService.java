package com.fishingbooker.ftn.service.interfaces;

import com.fishingbooker.ftn.bom.users.Administrator;
import com.fishingbooker.ftn.dto.AdminChangePasswordDto;
import com.fishingbooker.ftn.dto.ApplicationUserDto;

import java.util.List;

public interface AdministratorService {

    List<Administrator> findAll();

    Administrator findById(Long id);

    Administrator create(ApplicationUserDto userDto);

    public Administrator save(Administrator admin);

    void changePassword(AdminChangePasswordDto adminDto);
}
