package com.fishingbooker.ftn.service.interfaces;

import com.fishingbooker.ftn.bom.users.RegisteredClient;
import com.fishingbooker.ftn.dto.ApplicationUserDto;
import com.fishingbooker.ftn.dto.RegisteredClientDto;

import java.util.List;

public interface RegisteredClientService {

    List<RegisteredClientDto> findAll();

    RegisteredClientDto findById(Long id);

    RegisteredClientDto findByEmail(String email);

    RegisteredClient create(ApplicationUserDto userDto);

    void update(RegisteredClientDto userDto);

    List<RegisteredClient> getEnabledClients();

    RegisteredClient delete(Long id);
}
