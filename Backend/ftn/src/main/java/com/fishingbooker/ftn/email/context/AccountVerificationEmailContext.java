package com.fishingbooker.ftn.email.context;

import com.fishingbooker.ftn.bom.users.ApplicationUser;
import com.fishingbooker.ftn.email.context.AbstractEmailContext;
import org.springframework.web.util.UriComponentsBuilder;

public class AccountVerificationEmailContext extends AbstractEmailContext {

    private String token;

    @Override
    public <T> void init(T context){
        //we can do any common configuration setup here
        // like setting up some base URL and context
        ApplicationUser user = (ApplicationUser) context;
        put("firstName", user.getName());
        setTemplateLocation("emails/email-verification");
        setSubject("Complete your registration");
        setFrom("no-reply@fishing-booker.com");
        setTo(user.getEmail());
    }

    public void setToken(String token) {
        this.token = token;
        put("token", token);
    }

    public void buildVerificationUrl(final String baseURL, final String token){
        final String url= UriComponentsBuilder.fromHttpUrl(baseURL)
                .path("/register/verify").queryParam("token", token).toUriString();
        put("verificationURL", url);
    }
}
