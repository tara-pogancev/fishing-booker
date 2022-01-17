package com.fishingbooker.ftn.repository;

import com.fishingbooker.ftn.bom.users.RegistrationRequest;
import com.fishingbooker.ftn.repository.base.EntityRepository;
import org.springframework.data.jpa.repository.Lock;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.QueryHints;
import org.springframework.stereotype.Repository;

import javax.persistence.LockModeType;
import javax.persistence.QueryHint;
import java.util.List;

@Repository
public interface RegistrationRequestRepository extends EntityRepository<RegistrationRequest> {

    @Query("SELECT r FROM RegistrationRequest r WHERE r.approved=com.fishingbooker.ftn.bom.RequestApproval.WAITING")
    List<RegistrationRequest> getWaitingRequests();

    @Lock(LockModeType.PESSIMISTIC_WRITE)
    @Query("SELECT r FROM RegistrationRequest r WHERE r.id=:id")
    @QueryHints({@QueryHint(name = "javax.persistence.lock.timeout", value = "0")})
    RegistrationRequest getRequest(Long id);
}
