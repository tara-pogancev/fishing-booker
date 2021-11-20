package com.fishingbooker.ftn.service.interfaces;

import com.fishingbooker.ftn.bom.RegistrationRequest;
import com.fishingbooker.ftn.dto.RejectRequestDto;

import java.util.List;

public interface RegistrationRequestService {

    List<RegistrationRequest> get();

    void approveRequest(Long id);

    void rejectRequest(RejectRequestDto requestDto);
}
