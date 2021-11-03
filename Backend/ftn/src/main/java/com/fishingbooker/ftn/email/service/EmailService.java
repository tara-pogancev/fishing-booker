package com.fishingbooker.ftn.email.service;


import com.fishingbooker.ftn.email.context.AbstractEmailContext;
import org.springframework.mail.javamail.JavaMailSender;

import javax.mail.MessagingException;

public interface EmailService {

    void sendMail(final AbstractEmailContext email) throws MessagingException;

}