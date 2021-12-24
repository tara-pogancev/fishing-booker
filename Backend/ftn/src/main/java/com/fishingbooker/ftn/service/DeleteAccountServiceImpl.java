package com.fishingbooker.ftn.service;

import com.fishingbooker.ftn.bom.users.DeleteAccountRequest;
import com.fishingbooker.ftn.bom.RequestApproval;
import com.fishingbooker.ftn.bom.users.ApplicationUser;
import com.fishingbooker.ftn.repository.ApplicationUserRepository;
import com.fishingbooker.ftn.repository.DeleteAccountRequestRepository;
import com.fishingbooker.ftn.service.interfaces.DeleteAccountService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional
public class DeleteAccountServiceImpl implements DeleteAccountService {

    private final DeleteAccountRequestRepository deleteAccountRequestRepository;
    private final ApplicationUserRepository applicationUserRepository;
    private final MailingServiceImpl mailingService;

    @Override
    public List<DeleteAccountRequest> get() {
        return deleteAccountRequestRepository.findAll();
    }

    @Override
    public boolean approve(Long requestId,String description) {
        boolean result=false;
        ApplicationUser applicationUser;
        if (deleteAccountRequestRepository.exists(requestId)){
            DeleteAccountRequest request=deleteAccountRequestRepository.get(requestId);
            request.setRequestStatus(RequestApproval.APPROVED);
            deleteAccountRequestRepository.save(request);
            applicationUser=applicationUserRepository.get(request.getUserId());
            mailingService.sendAcceptDeleteAccountMail(applicationUser,description);
            applicationUserRepository.delete(applicationUser);
            result=true;
        }
        return result;
    }

    @Override
    public boolean reject(Long requestId) {
        boolean result=false;
        if (deleteAccountRequestRepository.exists(requestId)){
            DeleteAccountRequest request=deleteAccountRequestRepository.get(requestId);
            request.setRequestStatus(RequestApproval.DECLINED);
            deleteAccountRequestRepository.save(request);
            result=true;
        }
        return result;
    }

    @Override
    public Long create(DeleteAccountRequest deleteAccountRequest) {
        DeleteAccountRequest request=deleteAccountRequestRepository.save(deleteAccountRequest);
        return request.getId();
    }
}