package com.fishingbooker.ftn.controller;

import com.fishingbooker.ftn.bom.RegistrationRequest;
import com.fishingbooker.ftn.conversion.DataConverter;
import com.fishingbooker.ftn.dto.RegistrationRequestDto;
import com.fishingbooker.ftn.service.interfaces.RegistrationRequestService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/registration-requests")
public class RegistrationRequestController {

    private final RegistrationRequestService registratinRequestService;
    private final DataConverter converter;
    @GetMapping
    public List<RegistrationRequestDto> Get(){
        List<RegistrationRequest> registrationRequests=registratinRequestService.get();
        List<RegistrationRequestDto> registrationRequestDtos= converter.convert(registrationRequests,RegistrationRequestDto.class);
        return registrationRequestDtos;
    }
}
