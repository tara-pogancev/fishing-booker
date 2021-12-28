package com.fishingbooker.ftn.email.context;

import com.fishingbooker.ftn.bom.users.ApplicationUser;

public class AcceptDeleteAccountEmailContext extends AbstractEmailContext {

    @Override
    public <T> void init(T context) {
        //we can do any common configuration setup here
        // like setting up some base URL and context
        ApplicationUser user = (ApplicationUser) context;
        put("firstName", user.getName());
        setTemplateLocation("emails/accept-delete-account");
        setSubject("Delete Account Request");
        setFrom("no-reply@fishing-booker.com");
        setTo(user.getEmail());
    }

    public void setDescription(String description) {
        put("description", description);
    }
}
