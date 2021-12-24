package com.fishingbooker.ftn.service;

import com.fishingbooker.ftn.bom.users.ApplicationUser;
import com.fishingbooker.ftn.email.context.AcceptDeleteAccountEmailContext;
import com.fishingbooker.ftn.email.context.AcceptRegistrationEmailContext;
import com.fishingbooker.ftn.email.service.EmailService;
import com.fishingbooker.ftn.service.interfaces.MailingService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.mail.MessagingException;

@Service
@RequiredArgsConstructor
public class MailingServiceImpl implements MailingService {

    private final EmailService emailService;

    @Override
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
}