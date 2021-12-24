package com.fishingbooker.ftn.service.interfaces;

import com.fishingbooker.ftn.bom.users.RegistrationRequest;
import com.fishingbooker.ftn.dto.AdminResponseDto;

import java.util.List;

public interface RegistrationRequestService {

    List<RegistrationRequest> get();

    void approveRequest(Long id);

    void rejectRequest(AdminResponseDto requestDto);


}
