package com.fishingbooker.ftn.repository;

import com.fishingbooker.ftn.bom.complaints.Complaint;
import com.fishingbooker.ftn.repository.base.EntityRepository;
import org.springframework.data.jpa.repository.Lock;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.QueryHints;

import javax.persistence.LockModeType;
import javax.persistence.QueryHint;
import java.util.List;

public interface ComplaintRepository extends EntityRepository<Complaint> {

    @Query("SELECT c FROM Complaint c WHERE c.approval = com.fishingbooker.ftn.bom.RequestApproval.WAITING")
    List<Complaint> getWaitingComplaints();

    @Query("SELECT c FROM Complaint c WHERE c.id=:complaintId")
    @Lock(LockModeType.PESSIMISTIC_WRITE)
    @QueryHints({@QueryHint(name = "javax.persistence.lock.timeout", value ="0")})
    Complaint getComplaint(Long complaintId);
}
