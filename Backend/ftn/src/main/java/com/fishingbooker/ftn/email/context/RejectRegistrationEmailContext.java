package com.fishingbooker.ftn.email.context;

import com.fishingbooker.ftn.bom.users.ApplicationUser;
import org.springframework.web.util.UriComponentsBuilder;

public class RejectRegistrationEmailContext extends AbstractEmailContext {

    @Override
    public <T> void init(T context) {
        //we can do any common configuration setup here
        // like setting up some base URL and context
        ApplicationUser user = (ApplicationUser) context;
        put("firstName", user.getName());
        setTemplateLocation("emails/refused-registration");
        setSubject("Refused Registration");
        setFrom("no-reply@fishing-booker.com");
        setTo(user.getEmail());
    }

    public void setRejectionCause(final String rejectionCause) {
        put("rejectionCause", rejectionCause);
    }
}
