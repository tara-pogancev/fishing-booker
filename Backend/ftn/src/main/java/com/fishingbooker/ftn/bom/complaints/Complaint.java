package com.fishingbooker.ftn.bom.complaints;


import com.fishingbooker.ftn.bom.DatabaseEntity;
import com.fishingbooker.ftn.bom.RequestApproval;
import lombok.Data;

import javax.persistence.Column;
import javax.persistence.MappedSuperclass;

@MappedSuperclass
@Data
public class Complaint extends DatabaseEntity {

    @Column(name = "description", nullable = false)
    private String description;

    @Column(name = "approved", nullable = false)
    private RequestApproval approved = RequestApproval.WAITING;

    @Column(name = "complaintOwnerUsername", nullable = false)
    private String complaintOwnerUsername;

}
