package com.fishingbooker.ftn.controller;

import com.fishingbooker.ftn.bom.RequestApproval;
import com.fishingbooker.ftn.bom.users.DeleteAccountRequest;
import com.fishingbooker.ftn.conversion.DataConverter;
import com.fishingbooker.ftn.dto.AdminResponseDto;
import com.fishingbooker.ftn.dto.CreateDeleteAccountRequestDto;
import com.fishingbooker.ftn.dto.DeleteAccountRequestDto;
import com.fishingbooker.ftn.service.interfaces.DeleteAccountService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/delete-account")
@RequiredArgsConstructor
public class DeleteAccountController {

    private final DeleteAccountService deleteAccountService;
    private final DataConverter converter;

    @GetMapping()
    @PreAuthorize("hasRole('ADMINISTRATOR')")
    public List<DeleteAccountRequestDto> get() {
        List<DeleteAccountRequest> requests = deleteAccountService.get();
        List<DeleteAccountRequestDto> dtos = converter.convert(requests, DeleteAccountRequestDto.class);
        return dtos;
    }

    @PostMapping()
    @PreAuthorize("hasAnyRole('REGISTERED_CLIENT', 'COTTAGE_OWNER', 'FISHING_INSTRUCTOR', 'BOAT_OWNER')")
    public Long createRequest(@RequestBody CreateDeleteAccountRequestDto dto) {
        DeleteAccountRequest deleteAccountRequest = new DeleteAccountRequest(dto.getUserId(), dto.getDescription(), RequestApproval.WAITING);
        return deleteAccountService.create(deleteAccountRequest);
    }

    @PutMapping("/approve")
    @PreAuthorize("hasRole('ADMINISTRATOR')")
    public boolean approveRequest(@RequestBody AdminResponseDto responseDto) {
        return deleteAccountService.
                approve(responseDto.getId(),
                        responseDto.getDescription());
    }

    @PutMapping("/reject")
    @PreAuthorize("hasRole('ADMINISTRATOR')")
    public boolean rejectRequest(@RequestBody AdminResponseDto responseDto) {
        return deleteAccountService.reject(responseDto.getId(), responseDto.getDescription());
    }

    @GetMapping("/active-request/{id}")
    @PreAuthorize("hasRole('REGISTERED_CLIENT')")
    public Boolean clientHasActiveRequest(@PathVariable Long id) {
        return deleteAccountService.clientHasActiveRequest(id);
    }


}
