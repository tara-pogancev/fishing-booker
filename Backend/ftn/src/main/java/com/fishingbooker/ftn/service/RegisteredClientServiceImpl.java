package com.fishingbooker.ftn.service;

import com.fishingbooker.ftn.bom.users.RegisteredClient;
import com.fishingbooker.ftn.conversion.DataConverter;
import com.fishingbooker.ftn.dto.ApplicationUserDto;
import com.fishingbooker.ftn.repository.RegisteredClientRepository;
import com.fishingbooker.ftn.service.interfaces.RegisteredClientService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class RegisteredClientServiceImpl implements RegisteredClientService {

    private final RegisteredClientRepository clientRepository;
    private final DataConverter converter;

    @Override
    public List<RegisteredClient> findAll() {
        return clientRepository.findAll();
    }

    @Override
    public RegisteredClient findById(Long id) {
        return clientRepository.get(id);
    }

    @Override
    public RegisteredClient create(ApplicationUserDto userDto) {
        RegisteredClient client = converter.convert(userDto, RegisteredClient.class);
        return clientRepository.save(client);
    }

}
