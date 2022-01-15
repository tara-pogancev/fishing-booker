package com.fishingbooker.ftn.repository;

import com.fishingbooker.ftn.bom.users.DeleteAccountRequest;
import com.fishingbooker.ftn.repository.base.EntityRepository;
import org.springframework.data.jpa.repository.Lock;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.QueryHints;

import javax.persistence.LockModeType;
import javax.persistence.QueryHint;
import java.util.List;

public interface DeleteAccountRequestRepository extends EntityRepository<DeleteAccountRequest> {
    @Query("SELECT r FROM DeleteAccountRequest r where r.requestStatus=com.fishingbooker.ftn.bom.RequestApproval.WAITING")
    List<DeleteAccountRequest> getUnansweredRequests();

    @Lock(LockModeType.PESSIMISTIC_WRITE)
    @Query("SELECT r FROM DeleteAccountRequest r WHERE r.id=:requestId")
    @QueryHints({@QueryHint(name = "javax.persistence.lock.timeout", value ="0")})
    DeleteAccountRequest getRequest(Long requestId);
}
