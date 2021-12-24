package com.fishingbooker.ftn.service;

import com.fishingbooker.ftn.bom.users.RegistrationRequest;
import com.fishingbooker.ftn.bom.RequestApproval;
import com.fishingbooker.ftn.bom.users.ApplicationUser;
import com.fishingbooker.ftn.dto.AdminResponseDto;
import com.fishingbooker.ftn.email.context.AcceptRegistrationEmailContext;
import com.fishingbooker.ftn.email.context.RejectRegistrationEmailContext;
import com.fishingbooker.ftn.email.service.EmailService;
import com.fishingbooker.ftn.repository.ApplicationUserRepository;
import com.fishingbooker.ftn.repository.RegistrationRequestRepository;
import com.fishingbooker.ftn.service.interfaces.RegistrationRequestService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.mail.MessagingException;
import javax.transaction.Transactional;
import java.util.List;

@Service
@Transactional
@RequiredArgsConstructor
public class RegistrationRequestServiceImpl implements RegistrationRequestService {

    private final RegistrationRequestRepository registrationRequestRepository;
    private final ApplicationUserRepository applicationUserRepository;
    private final EmailService emailService;

    @Override
    public List<RegistrationRequest> get() {
        return registrationRequestRepository.getWaitingRequests();
    }

    @Override
    public void approveRequest(Long id) {
        RegistrationRequest request = registrationRequestRepository.get(id);
        request.setApproved(RequestApproval.APPROVED);
        registrationRequestRepository.save(request);
        request.getUser().setEnabled(true);
        sendAcceptRegistrationEmail(request.getUser());
        applicationUserRepository.save(request.getUser());
    }

    @Override
    public void rejectRequest(AdminResponseDto requestDto) {
        RegistrationRequest request = registrationRequestRepository.get(requestDto.getId());
        request.setApproved(RequestApproval.DECLINED);
        request.setCausesOfRejection(requestDto.getDescription());
        sendRefuseRegistrationEmail(request.getUser(), requestDto.getDescription());
        registrationRequestRepository.save(request);
    }

    private void sendAcceptRegistrationEmail(ApplicationUser user) {

        AcceptRegistrationEmailContext emailContext = new AcceptRegistrationEmailContext();
        emailContext.init(user);
        try {
            emailService.sendMail(emailContext);
        } catch (MessagingException e) {
            e.printStackTrace();
        }
    }

    private void sendRefuseRegistrationEmail(ApplicationUser user, String rejectionCause) {
        RejectRegistrationEmailContext emailContext = new RejectRegistrationEmailContext();
        emailContext.init(user);
        emailContext.setRejectionCause(rejectionCause);

        try {
            emailService.sendMail(emailContext);
        } catch (MessagingException e) {
            e.printStackTrace();
        }
    }
}
