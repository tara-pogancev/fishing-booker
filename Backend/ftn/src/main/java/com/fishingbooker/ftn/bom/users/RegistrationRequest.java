package com.fishingbooker.ftn.bom.users;

import com.fishingbooker.ftn.bom.DatabaseEntity;
import com.fishingbooker.ftn.bom.RequestApproval;
import com.fishingbooker.ftn.bom.users.ApplicationUser;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Getter
@Setter
@Table(name = "RegistrationRequest")
public class RegistrationRequest extends DatabaseEntity {

    @OneToOne()
    @JoinColumn(name = "user_id", referencedColumnName = "id")
    private ApplicationUser user;

    @Column(name = "requestStatus", nullable = false)
    private RequestApproval approved = RequestApproval.WAITING;

    @Column(name = "causesOfRejection", nullable = true)
    private String causesOfRejection;

    @Column(name = "registrationDescription", nullable = true)
    private String registrationDescription;

}
