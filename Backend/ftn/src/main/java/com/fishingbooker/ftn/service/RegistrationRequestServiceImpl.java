package com.fishingbooker.ftn.service;

import com.fishingbooker.ftn.bom.RequestApproval;
import com.fishingbooker.ftn.bom.users.ApplicationUser;
import com.fishingbooker.ftn.bom.users.RegistrationRequest;
import com.fishingbooker.ftn.dto.AdminResponseDto;
import com.fishingbooker.ftn.email.context.AcceptRegistrationEmailContext;
import com.fishingbooker.ftn.email.context.RejectRegistrationEmailContext;
import com.fishingbooker.ftn.email.service.EmailService;
import com.fishingbooker.ftn.repository.ApplicationUserRepository;
import com.fishingbooker.ftn.repository.RegistrationRequestRepository;
import com.fishingbooker.ftn.service.interfaces.RegistrationRequestService;
import jdk.nashorn.internal.scripts.JD;
import lombok.RequiredArgsConstructor;
import org.hibernate.HibernateException;
import org.hibernate.JDBCException;
import org.hibernate.PessimisticLockException;
import org.springframework.dao.PessimisticLockingFailureException;
import org.springframework.orm.ObjectOptimisticLockingFailureException;
import org.springframework.stereotype.Service;

import javax.mail.MessagingException;
import javax.transaction.Transactional;
import java.sql.SQLException;
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
    public boolean approveRequest(Long id){

        boolean retVal=true;
        try{
            RegistrationRequest request = registrationRequestRepository.getRequest(id);
            if (request.getApproved()!=RequestApproval.WAITING){
                retVal=false;
            }else{
                request.setApproved(RequestApproval.APPROVED);
                registrationRequestRepository.save(request);
                request.getUser().setEnabled(true);
                applicationUserRepository.save(request.getUser());
                sendAcceptRegistrationEmail(request.getUser());
                retVal=true;
            }
        }catch (PessimisticLockingFailureException e){
            System.out.println("Exception pessimistic locking! Concurrent access");
            retVal=false;
        }

        return retVal;


    }

    @Override
    public boolean rejectRequest(AdminResponseDto requestDto) {
        boolean retVal=true;
        try{
            RegistrationRequest request = registrationRequestRepository.getRequest(requestDto.getId());
            if (request.getApproved()!=RequestApproval.WAITING){
                retVal=false;
            }else{
                request.setApproved(RequestApproval.DECLINED);
                request.setCausesOfRejection(requestDto.getDescription());
                registrationRequestRepository.save(request);
                sendRefuseRegistrationEmail(request.getUser(), requestDto.getDescription());
            }
        }catch (PessimisticLockingFailureException e){
            System.out.println("Exception pessimistic locking! Concurrent access");
            retVal=false;
        }
        return retVal;
    }

    @Transactional(Transactional.TxType.MANDATORY)
    private void sendAcceptRegistrationEmail(ApplicationUser user) {

        AcceptRegistrationEmailContext emailContext = new AcceptRegistrationEmailContext();
        emailContext.init(user);
        try {
            emailService.sendMail(emailContext);
        } catch (MessagingException e) {
            e.printStackTrace();
        }
    }

    @Transactional(Transactional.TxType.MANDATORY)
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
