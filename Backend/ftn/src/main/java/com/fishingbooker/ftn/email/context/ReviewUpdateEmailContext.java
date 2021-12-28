package com.fishingbooker.ftn.email.context;

import com.fishingbooker.ftn.bom.users.ApplicationUser;

public class ReviewUpdateEmailContext extends AbstractEmailContext {

    @Override
    public <T> void init(T context) {
        //we can do any common configuration setup here
        // like setting up some base URL and context
        ApplicationUser user = (ApplicationUser) context;
        put("firstName", user.getName());
        setTemplateLocation("emails/client-review-update");
        setSubject("We have updates about your review!");
        setFrom("no-reply@fishing-booker.com");
        setTo(user.getEmail());
    }

}