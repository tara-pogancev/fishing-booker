package com.fishingbooker.ftn.service;

import com.fishingbooker.ftn.bom.Address;
import com.fishingbooker.ftn.bom.users.RegisteredClient;
import com.fishingbooker.ftn.conversion.DataConverter;
import com.fishingbooker.ftn.dto.ApplicationUserDto;
import com.fishingbooker.ftn.dto.RegisteredClientDto;
import com.fishingbooker.ftn.repository.AddressRepository;
import com.fishingbooker.ftn.repository.RegisteredClientRepository;
import com.fishingbooker.ftn.service.interfaces.RegisteredClientService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
@Transactional
@RequiredArgsConstructor
public class RegisteredClientServiceImpl implements RegisteredClientService {

    private final RegisteredClientRepository clientRepository;
    private final AddressRepository addressRepository;
    private final DataConverter converter;

    @Override
    public List<RegisteredClientDto> findAll() {
        List<RegisteredClient> clientList = clientRepository.findAll();
        return converter.convert(clientList, RegisteredClientDto.class);
    }

    @Override
    public RegisteredClientDto findById(Long id) {
        RegisteredClient client = clientRepository.get(id);
        return converter.convert(client, RegisteredClientDto.class);
    }

    @Override
    public RegisteredClientDto findByEmail(String email) {
        RegisteredClientDto dto = null;
        for (RegisteredClient client : clientRepository.findAll()) {
            if (client.getEmail().equals(email)) {
                dto = converter.convert(client, RegisteredClientDto.class);
                break;
            }
        }
        return dto;
    }

    @Override
    public RegisteredClient create(ApplicationUserDto userDto) {
        RegisteredClient client = converter.convert(userDto, RegisteredClient.class);
        return clientRepository.save(client);
    }

    @Override
    public void update(RegisteredClientDto userDto) {
        RegisteredClient client = clientRepository.get(userDto.getId());
        client.setPassword(new BCryptPasswordEncoder().encode(userDto.getPassword()));
        client.setName(userDto.getName());
        client.setLastName(userDto.getLastName());
        client.setPhone(userDto.getPhone());
        Address clientAddress = client.getUserAddress();
        clientAddress.setStreet(userDto.getStreet());
        clientAddress.setCountry(userDto.getCountry());
        clientAddress.setCity(userDto.getCity());
        addressRepository.save(clientAddress);
        clientRepository.save(client);
    }

    @Override
    public List<RegisteredClient> getEnabledClients() {
        return clientRepository.getEnabledClients();
    }

    @Override
    public RegisteredClient delete(Long id) {
        RegisteredClient client=clientRepository.get(id);
        clientRepository.delete(client);
        return client;
    }

}
