package com.fishingbooker.ftn.service.interfaces;

import com.fishingbooker.ftn.bom.users.RegistrationRequest;
import com.fishingbooker.ftn.dto.AdminResponseDto;

import java.util.List;

public interface RegistrationRequestService {

    List<RegistrationRequest> get();

    boolean approveRequest(Long id);

    boolean rejectRequest(AdminResponseDto requestDto);


}
