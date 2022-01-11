package com.fishingbooker.ftn.email.context;

import com.fishingbooker.ftn.bom.users.ApplicationUser;

public class OwnerPenaltyNotification extends AbstractEmailContext {

    public <T> void init(T context) {
        //we can do any common configuration setup here
        // like setting up some base URL and context
        ApplicationUser user = (ApplicationUser) context;
        put("firstName", user.getName());
        setTemplateLocation("emails/owner-penalty-notification");
        setSubject("Penalty Notification!");
        setFrom("no-reply@fishing-booker.com");
        setTo(user.getEmail());
    }

    public void setDate(String clientName, String user) {
        put("clientName", clientName);
        put("user", user);
    }
}
