package com.fishingbooker.ftn.repository;

import com.fishingbooker.ftn.bom.complaints.Complaint;
import com.fishingbooker.ftn.bom.users.CottageOwner;
import com.fishingbooker.ftn.repository.base.EntityRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface ComplaintRepository extends EntityRepository<Complaint> {

    @Query("SELECT c FROM Complaint c WHERE c.approval = com.fishingbooker.ftn.bom.RequestApproval.WAITING")
    List<Complaint> getWaitingComplaints();
}
