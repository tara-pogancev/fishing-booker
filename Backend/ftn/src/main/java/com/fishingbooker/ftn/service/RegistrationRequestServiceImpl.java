package com.fishingbooker.ftn.service;

import com.fishingbooker.ftn.bom.RegistrationRequest;
import com.fishingbooker.ftn.repository.RegistrationRequestRepository;
import com.fishingbooker.ftn.service.interfaces.RegistrationRequestService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
@Transactional
@RequiredArgsConstructor
public class RegistrationRequestServiceImpl implements RegistrationRequestService {

    private final RegistrationRequestRepository registrationRequestRepository;
    @Override
    public List<RegistrationRequest> get() {
        return registrationRequestRepository.findAll();
    }
}
