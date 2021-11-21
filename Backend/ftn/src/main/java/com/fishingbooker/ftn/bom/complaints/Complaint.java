package com.fishingbooker.ftn.bom.complaints;


import com.fishingbooker.ftn.bom.DatabaseEntity;
import com.fishingbooker.ftn.bom.RequestApproval;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.MappedSuperclass;
import javax.persistence.Table;

@MappedSuperclass
@Getter
@Setter
@Table (name = "Complaint")
public class Complaint extends DatabaseEntity {

    @Column(name = "description", nullable = false)
    private String description;

    @Column(name = "approved", nullable = false)
    private RequestApproval approved = RequestApproval.WAITING;

    @Column(name = "complaintOwnerUsername", nullable = false)
    private String complaintOwnerUsername;

}
