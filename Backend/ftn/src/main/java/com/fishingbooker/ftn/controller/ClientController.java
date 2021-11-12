package com.fishingbooker.ftn.controller;

import com.fishingbooker.ftn.dto.RegisteredClientDto;
import com.fishingbooker.ftn.service.interfaces.RegisteredClientService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/client")
public class ClientController {

    private final RegisteredClientService clientService;

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

}
