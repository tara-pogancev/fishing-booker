package com.fishingbooker.ftn.repository;

import com.fishingbooker.ftn.bom.users.DeleteAccountRequest;
import com.fishingbooker.ftn.repository.base.EntityRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface DeleteAccountRequestRepository extends EntityRepository<DeleteAccountRequest> {
    @Query("SELECT r FROM DeleteAccountRequest r where r.requestStatus=com.fishingbooker.ftn.bom.RequestApproval.WAITING")
    List<DeleteAccountRequest> getUnansweredRequests();
}
