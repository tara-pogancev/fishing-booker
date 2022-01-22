package com.fishingbooker.ftn.service;

import com.fishingbooker.ftn.bom.users.ApplicationUser;
import com.fishingbooker.ftn.bom.users.RegisteredClient;
import com.fishingbooker.ftn.dto.ReservationDto;
import com.fishingbooker.ftn.email.context.*;
import com.fishingbooker.ftn.email.service.EmailService;
import com.fishingbooker.ftn.repository.RegistrationTokenRepository;
import com.fishingbooker.ftn.security.registration.RegistrationToken;
import com.fishingbooker.ftn.security.registration.RegistrationTokenService;
import com.fishingbooker.ftn.service.interfaces.MailingService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.annotation.Async;
import org.springframework.security.core.token.TokenService;
import org.springframework.stereotype.Service;

import javax.mail.MessagingException;

@Service
@RequiredArgsConstructor
public class MailingServiceImpl implements MailingService {

    private final EmailService emailService;
    private final RegistrationTokenService tokenService;
    private final RegistrationTokenRepository registrationTokenRepository;

    @Value("${site.base.url.https}")
    private String baseURL;

    @Override
    @Async
    public void sendAcceptDeleteAccountMail(ApplicationUser user, String description) {
        AcceptDeleteAccountEmailContext emailContext = new AcceptDeleteAccountEmailContext();
        emailContext.setDescription(description);
        emailContext.init(user);
        try {
            emailService.sendMail(emailContext);
        } catch (MessagingException e) {
            e.printStackTrace();
        }
    }

    @Override
    @Async
    public void sendRefuseDeleteAccountMail(ApplicationUser user, String description) {
        RefuseDeleteAccountEmailContext emailContext = new RefuseDeleteAccountEmailContext();
        emailContext.setDescription(description);
        emailContext.init(user);
        try {
            emailService.sendMail(emailContext);
        } catch (MessagingException e) {
            e.printStackTrace();
        }

    }

    @Override
    @Async
    public void sendComplaintResponse(ApplicationUser client, ApplicationUser owner, String response, String complaint) {
        ComplaintResponseEmailContext emailContext = new ComplaintResponseEmailContext();
        emailContext.setComplaint(complaint);
        emailContext.setDescription(response);
        emailContext.init(client);
        try {
            emailService.sendMail(emailContext);
        } catch (MessagingException e) {
            e.printStackTrace();
        }
        emailContext.init(owner);
        try {
            emailService.sendMail(emailContext);
        } catch (MessagingException e) {
            e.printStackTrace();
        }
    }

    @Override
    @Async
    public void sendMailToUsersAboutGivingPenalty(RegisteredClient registeredClient, ApplicationUser owner) {
        ClientPenaltyNotification emailContext = new ClientPenaltyNotification();
        emailContext.setDate(registeredClient.getFullName(), owner.getFullName());
        emailContext.init(registeredClient);
        try {
            emailService.sendMail(emailContext);
        } catch (MessagingException e) {
            e.printStackTrace();
        }

        OwnerPenaltyNotification ownerEmailContext = new OwnerPenaltyNotification();
        ownerEmailContext.setDate(registeredClient.getFullName(), owner.getFullName());
        ownerEmailContext.init(owner);
        try {
            emailService.sendMail(ownerEmailContext);
        } catch (MessagingException e) {
            e.printStackTrace();
        }
    }

    @Override
    @Async
    public void sendMailToUsersAboutNotGivingPenalty(RegisteredClient registeredClient, ApplicationUser owner) {
        ClientNoPenaltyNotification emailContext = new ClientNoPenaltyNotification();
        emailContext.setDate(registeredClient.getFullName(), owner.getFullName());
        emailContext.init(registeredClient);
        try {
            emailService.sendMail(emailContext);
        } catch (MessagingException e) {
            e.printStackTrace();
        }

        OwnerNoPenaltyNotification ownerEmailContext = new OwnerNoPenaltyNotification();
        ownerEmailContext.setDate(registeredClient.getFullName(), owner.getFullName());
        ownerEmailContext.init(owner);
        try {
            emailService.sendMail(ownerEmailContext);
        } catch (MessagingException e) {
            e.printStackTrace();
        }
    }

    @Override
    @Async
    public void sendMailToSubscribedUsers(RegisteredClient client, String fullName) {
        QuickActionSubscription emailContext = new QuickActionSubscription();
        emailContext.setDate(client.getFullName(), fullName);
        emailContext.init(client);
        try {
            emailService.sendMail(emailContext);
        } catch (MessagingException e) {
            e.printStackTrace();
        }
    }

    @Override
    @Async
    public void sendMailToUserAboutNewReservation(ApplicationUser instructor, RegisteredClient client) {
        InstructorCreateNewReservation emailContext=new InstructorCreateNewReservation();
        emailContext.setDate(client.getFullName(),instructor.getFullName());
        emailContext.init(client);
        try {
            emailService.sendMail(emailContext);
        } catch (MessagingException e) {
            e.printStackTrace();
        }
    }

    @Override
    // @Async
    public void sendReservationConfirmationEmail(ApplicationUser user, ReservationDto reservationDto) {
        ClientReservationConfirmationEmailContext emailContext = new ClientReservationConfirmationEmailContext();
        emailContext.init(user);
        emailContext.setReservationData(reservationDto);
        try {
            emailService.sendMail(emailContext);
        } catch (MessagingException e) {
            e.printStackTrace();
        }
    }

}
