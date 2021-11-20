package com.fishingbooker.ftn.repository;

import com.fishingbooker.ftn.bom.RegistrationRequest;
import com.fishingbooker.ftn.repository.base.EntityRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RegistrationRequestRepository extends EntityRepository<RegistrationRequest> {

    @Query("SELECT r FROM RegistrationRequest r WHERE r.approved=com.fishingbooker.ftn.bom.RequestApproval.WAITING")
    List<RegistrationRequest> getWaitingRequests();
}
