package com.fishingbooker.ftn.service.interfaces;

import com.fishingbooker.ftn.bom.users.ApplicationUser;

public interface MailingService {

    void sendAcceptDeleteAccountMail(ApplicationUser user, String desciption);
}