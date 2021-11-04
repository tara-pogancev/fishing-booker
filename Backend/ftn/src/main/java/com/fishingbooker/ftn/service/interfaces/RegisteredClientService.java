package com.fishingbooker.ftn.service.interfaces;

import com.fishingbooker.ftn.bom.users.RegisteredClient;
import com.fishingbooker.ftn.dto.ApplicationUserDto;

import java.util.List;

public interface RegisteredClientService {

    List<RegisteredClient> findAll();

    RegisteredClient findById(Long id);

    RegisteredClient create(ApplicationUserDto userDto);

}
