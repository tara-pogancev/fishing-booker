package com.fishingbooker.ftn.security.registration;

import com.fishingbooker.ftn.repository.RegistrationTokenRepository;
import org.apache.tomcat.util.codec.binary.Base64;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.keygen.BytesKeyGenerator;
import org.springframework.security.crypto.keygen.KeyGenerators;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.nio.charset.Charset;
import java.time.LocalDateTime;

@Service
@Transactional
public class RegistrationTokenServiceImpl implements RegistrationTokenService {

    private static final BytesKeyGenerator DEFAULT_TOKEN_GENERATOR = KeyGenerators.secureRandom(15);
    private static final Charset US_ASCII = Charset.forName("US-ASCII");

    private int tokenValidityInSeconds = 28800; // Is valid for 8h

    @Autowired
    RegistrationTokenRepository registrationTokenRepository;

    @Override
    public RegistrationToken createSecureToken() {
        String tokenValue = new String(Base64.encodeBase64URLSafe(DEFAULT_TOKEN_GENERATOR.generateKey()), US_ASCII); // this is a sample, you can adapt as per your security need
        RegistrationToken registrationToken = new RegistrationToken();
        registrationToken.setToken(tokenValue);
        registrationToken.setExpireAt(LocalDateTime.now().plusSeconds(getTokenValidityInSeconds()));
        this.saveRegistrationToken(registrationToken);
        return registrationToken;
    }

    @Override
    public void saveRegistrationToken(RegistrationToken token) {
        registrationTokenRepository.save(token);
    }

    @Override
    public RegistrationToken findByToken(String token) {
        return registrationTokenRepository.findByToken(token);
    }

    @Override
    public void removeToken(RegistrationToken token) {
        registrationTokenRepository.delete(token);
    }

    @Override
    public void removeTokenByToken(String token) {
        registrationTokenRepository.removeByToken(token);
    }

    public int getTokenValidityInSeconds() {
        return tokenValidityInSeconds;
    }

}
