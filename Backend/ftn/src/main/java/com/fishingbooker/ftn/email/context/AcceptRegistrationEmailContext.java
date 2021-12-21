package com.fishingbooker.ftn.email.context;

import com.fishingbooker.ftn.bom.users.ApplicationUser;

public class AcceptRegistrationEmailContext extends AbstractEmailContext {

    @Override
    public <T> void init(T context) {
        //we can do any common configuration setup here
        // like setting up some base URL and context
        ApplicationUser user = (ApplicationUser) context;
        put("firstName", user.getName());
        setTemplateLocation("emails/accept-registration");
        setSubject("Complete your registration");
        setFrom("no-reply@fishing-booker.com");
        setTo(user.getEmail());
    }
}
