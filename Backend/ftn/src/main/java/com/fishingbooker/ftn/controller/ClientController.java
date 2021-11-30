package com.fishingbooker.ftn.controller;

import com.fishingbooker.ftn.bom.users.RegisteredClient;
import com.fishingbooker.ftn.conversion.DataConverter;
import com.fishingbooker.ftn.dto.AdminViewUserDto;
import com.fishingbooker.ftn.dto.RegisteredClientDto;
import com.fishingbooker.ftn.service.interfaces.RegisteredClientService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/client")
public class ClientController {

    private final RegisteredClientService clientService;
    private final DataConverter converter;
    @GetMapping
    public List<RegisteredClientDto> findAll() {
        return clientService.findAll();
    }

    @GetMapping("/email/{email}")
    public RegisteredClientDto findByEmail(@PathVariable String email) {
        return clientService.findByEmail(email);
    }

    @GetMapping("/{id}")
    public RegisteredClientDto findById(@PathVariable String id) {
        Long idNum = Long.parseLong(id);
        return clientService.findById(idNum);
    }

    @PutMapping
    public void update(@RequestBody RegisteredClientDto dto) {
        clientService.update(dto);
    }

    @GetMapping("/get-enabled")
    public List<RegisteredClientDto> getEnabledClients(){
        List<RegisteredClient> clients=clientService.getEnabledClients();
        return converter.convert(clients,RegisteredClientDto.class);
    }

    @DeleteMapping("/delete-client/{id}")
    public ResponseEntity<Long> deleteClient(@PathVariable("id") Long id){
        return new ResponseEntity<>(clientService.delete(id).getId(), HttpStatus.OK);
    }


}
