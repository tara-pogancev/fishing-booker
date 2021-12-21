package com.fishingbooker.ftn.bom.users;

import com.fishingbooker.ftn.bom.DatabaseEntity;
import com.fishingbooker.ftn.bom.RequestApproval;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Getter
@Setter
@Table(name = "AccountRemovalRequest")
public class AccountRemovalRequest extends DatabaseEntity {

    @Column(name = "username", nullable = false)
    private String username;    // Username of the user who wants his account removed

    @Column(name = "text", nullable = false)
    private String text;

    @Column(name = "approval", nullable = false)
    private RequestApproval approval = RequestApproval.WAITING;

}
