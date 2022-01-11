package com.fishingbooker.ftn.service.interfaces;

import com.fishingbooker.ftn.bom.users.ApplicationUser;
import com.fishingbooker.ftn.bom.users.FishingInstructor;
import com.fishingbooker.ftn.bom.users.RegisteredClient;

public interface MailingService {

    void sendAcceptDeleteAccountMail(ApplicationUser user, String desciption);

    void sendRefuseDeleteAccountMail(ApplicationUser user, String description);

    void sendComplaintResponse(ApplicationUser client, ApplicationUser owner,String response,String complaint);

    void sendMailToUsersAboutGivingPenalty(RegisteredClient registeredClient, ApplicationUser owner);

    void sendMailToUsersAboutNotGivingPenalty(RegisteredClient registeredClient, ApplicationUser owner);

    void sendMailToSubscribedUsers(RegisteredClient client, String fullName);
}
