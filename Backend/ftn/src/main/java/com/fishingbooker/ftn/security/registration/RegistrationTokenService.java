package com.fishingbooker.ftn.security.registration;


public interface RegistrationTokenService {

    RegistrationToken createSecureToken();

    void saveRegistrationToken(final RegistrationToken token);

    RegistrationToken findByToken(final String token);

    void removeToken(final RegistrationToken token);

    void removeTokenByToken(final String token);

}
