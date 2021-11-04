package com.fishingbooker.ftn.email.service;


import com.fishingbooker.ftn.email.context.AbstractEmailContext;

import javax.mail.MessagingException;

public interface EmailService {

    void sendMail(final AbstractEmailContext email) throws MessagingException;

}