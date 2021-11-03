package com.fishingbooker.ftn.repository;

import com.fishingbooker.ftn.repository.base.EntityRepository;
import com.fishingbooker.ftn.security.registration.RegistrationToken;
import org.springframework.stereotype.Repository;

@Repository
public interface RegistrationTokenRepository extends EntityRepository<RegistrationToken> {

    RegistrationToken findByToken (final String token);
    Long removeByToken (String token);

}
