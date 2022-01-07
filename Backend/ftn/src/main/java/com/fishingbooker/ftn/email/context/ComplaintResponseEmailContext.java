package com.fishingbooker.ftn.email.context;

import com.fishingbooker.ftn.bom.users.ApplicationUser;

public class ComplaintResponseEmailContext extends AbstractEmailContext {

    @Override
    public <T> void init(T context) {
        //we can do any common configuration setup here
        // like setting up some base URL and context
        ApplicationUser user = (ApplicationUser) context;
        put("firstName", user.getName());
        setTemplateLocation("emails/complaint-response");
        setSubject("Complaint Response");
        setFrom("no-reply@fishing-booker.com");
        setTo(user.getEmail());
    }

    public void setDescription(String response) {
        put("response", response);
    }

    public void setComplaint(String complaint) {
        put("complaint", complaint);
    }
}
