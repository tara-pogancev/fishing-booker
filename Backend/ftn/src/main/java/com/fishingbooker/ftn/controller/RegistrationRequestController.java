package com.fishingbooker.ftn.controller;

import com.fishingbooker.ftn.bom.users.RegistrationRequest;
import com.fishingbooker.ftn.conversion.DataConverter;
import com.fishingbooker.ftn.dto.AdminResponseDto;
import com.fishingbooker.ftn.dto.RegistrationRequestDto;
import com.fishingbooker.ftn.service.interfaces.RegistrationRequestService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/registration-requests")
public class RegistrationRequestController {

    private final RegistrationRequestService registratinRequestService;
    private final DataConverter converter;

    @GetMapping
    public List<RegistrationRequestDto> Get() {
        List<RegistrationRequest> registrationRequests = registratinRequestService.get();
        List<RegistrationRequestDto> registrationRequestDtos = converter.convert(registrationRequests, RegistrationRequestDto.class);
        return registrationRequestDtos;
    }

    @PutMapping("/approve/{id}")
    public boolean approveRequest(@PathVariable("id") Long id) {
        return registratinRequestService.approveRequest(id);
    }

    @PutMapping("/reject")
    public boolean rejectRequest(@RequestBody AdminResponseDto requestDto) {
        return registratinRequestService.rejectRequest(requestDto);
    }
}
